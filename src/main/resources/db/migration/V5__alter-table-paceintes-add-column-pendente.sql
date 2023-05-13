alter table paciente add pendente tinyint;
update paciente set pendente = 1;