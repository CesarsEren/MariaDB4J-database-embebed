package com.alo.digital.database.mariadb;

import ch.vorburger.exec.ManagedProcessException;
import ch.vorburger.mariadb4j.DB;
import ch.vorburger.mariadb4j.springframework.MariaDB4jSpringService;

//import java.util.logging.Logger;

import com.alo.digital.database.util.SrvUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;

public class CustomerMariaDB4SpringService extends MariaDB4jSpringService {

    private static Logger logger = LoggerFactory.getLogger(CustomerMariaDB4SpringService.class);

    private String pathSql;
    private String restartCommand;

    @Override
    public void start() {
        try {
            super.start();
            if (isRunning()) {
                DB db = getDB();
                db.source(pathSql);
            } else {
                throw new IllegalStateException("MariaDB4j is not running");
            }
        } catch (ManagedProcessException e) {
            throw new IllegalStateException("Error al ejecutar sqlFiles", e);
        }
    }

    @Value("sql/db_empresa.sql")
    public void setPathSql(String pathSql) {
        this.pathSql = pathSql;
    }

    @Value("${app.srvRestart}")
    public void setRestartCommand(String restartCommand) {
        this.restartCommand = restartCommand;
    }

    @Scheduled(cron="0 0/2 * * * *")
    public void isAlive(){
        try {
            if(!isRunning()){
                logger.error("Base de datos detenidos, Reiniciando DB");
                SrvUtil.exec(restartCommand);
            }
        }catch (Exception e){
            logger.error("Database is stopped. {}={}",e.getClass().getSimpleName(),e.getMessage(),e);
            SrvUtil.exec(restartCommand);
        }
    }
}
