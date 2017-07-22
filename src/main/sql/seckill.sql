--定义存储过程
DELIMITER $$ 
create procedure `seckill`.`execute_seckill`
(in v_seckill_id bigint , in V_phone bigint , 
in v_kill_time timestamp , out r_result int)
	begin
		declare insert_count int default 0;
		START TRANSACTION;
		insert ignore into success_killed
			(seckill_id,user_phone,create_time)
			values (v_seckill_id,v_phone,v_kill_time);
		select row_count() into insert_count;
		IF (insert_count = 0) then
			rollback;
			set r_result = - 1;
		elseIF(insert_count < 0) then
			rollback;
			set r_result = -2 ;
		else 
			update seckill set number = number - 1 
			 	where seckill_id = v_seckill_id and create_time < v_kill_time and
			 		end_time > v_kill_time and number > 0 ;
			select  ROW_COUNT() into insert_count;
			if (insert_count = 0 ) then
			 	ROLLBACK;
				SET r_result = 0;
			ELSEIF (insert_count < 0) THEN
				ROLLBACK;
				SET r_result = -2; 
			ELSE
				COMMIT;
				SET r_result = 1;
			END IF;
		END IF;
	END;
$$
DELIMITER ;
set @r_result=-3;
--执行存储过程
call execute_seckill(1001, 13631231234, now(), @r_result);

SELECT @r_result;

