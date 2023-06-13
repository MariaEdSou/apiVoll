package med.voll.api.controller;

import med.voll.api.consulta.DadosAgendamentoConsulta;
import med.voll.api.domain.consulta.AgendaDeConsultas;
import med.voll.api.domain.consulta.DadosDetalhamentoConsulta;
import med.voll.api.domain.medico.Especialidade;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.awt.*;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.InstanceOfAssertFactories.LOCAL_DATE_TIME;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
class ConsultaControllerTest {
    @Autowired
    private MockMvc mvc;
    //tem que ser o mesmo tipo que eu recebo no mmetodo do controller
    // um json que chega na api
    @Autowired
    private JacksonTester<DadosAgendamentoConsulta> dadosAgendamentoConsultaJson;
    //json que e devolvido pela api, vai de acordo com oq e devolvido no metodo do controller

    @Autowired
    private JacksonTester<DadosDetalhamentoConsulta> dadosDetalhamentoConsultaJson;
    @MockBean
    private AgendaDeConsultas agendaDeConsultas;

    @Test
    @DisplayName("Deveria devolver cogido http 400 quando informacoes estao inalidas")
    @WithMockUser
    void agendar_cenario1() throws Exception {
        var respose = mvc.perform(post("/consultas"))
                .andReturn().getResponse();

        assertThat(respose.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());

    }

    @Test
    @DisplayName("Deveria devolver codigo http 200 quando informacoes estao validas")
    @WithMockUser
    void agendar_cenario2() throws Exception {
        var data = LocalDateTime.now().plusHours(1);
        var especialidade = Especialidade.CARDIOLOGIA;
        var dadosDetalhamento = new DadosDetalhamentoConsulta(null, 2l, 5l, data);
//when, mokito qwuando a agenda que e um mock tiver o metodo sgendar chamado, passando qualquer parametro, retorne dadosDetalhamento
        when(agendaDeConsultas.agendar(any())).thenReturn(dadosDetalhamento);

        var response = mvc
                //metodo pst p barra consultas
                .perform(post("/consultas")
                        //content p levar um cabecalho http contentTipe com valor json
                        .contentType(MediaType.APPLICATION_JSON)
                        //p gerar um json injeta um jacksonTester, passa o objeto que ele converte p json com getJson
                        .content(dadosAgendamentoConsultaJson.write(new DadosAgendamentoConsulta(2l, 5l, data, especialidade)
                        ).getJson()))
                .andReturn().getResponse();
// verifica se o codigo devolvido e o 200
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        var jsonEsperado = dadosDetalhamentoConsultaJson.write(dadosDetalhamento).getJson();
        //pega o conteudo com corpo da resposta como string e verifica se e igual o json esperado

        assertThat(response.getContentAsString()).isEqualTo(jsonEsperado);
    }


}