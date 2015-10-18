CREATE DEFINER=`root`@`localhost` PROCEDURE `createFileHome`(id_usuario int)
BEGIN
	SET @id_pasta = (SELECT Auto_increment FROM information_schema.tables WHERE table_name='pasta');
	INSERT INTO pasta (`id`, `id_pasta_pai`,	`nome`,	`data_criacao`)	VALUES	(@id_pasta,	@id_pasta, "Início", NOW());
    INSERT INTO user_has_pasta (`user_id`, `pasta_id`) VALUES (id_usuario, @id_pasta);
END