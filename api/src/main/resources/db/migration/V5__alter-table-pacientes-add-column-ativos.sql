ALTER TABLE pacientes ADD ativo tinyint;
update pacientes set ativo = 1;
