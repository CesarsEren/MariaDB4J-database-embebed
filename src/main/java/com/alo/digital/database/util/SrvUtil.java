package com.alo.digital.database.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SrvUtil {

    private static Logger logger = LoggerFactory.getLogger(SrvUtil.class);

    private SrvUtil() {}

    /**
     * Execute the command after {@code after} seconds.
     * @param command Command to execute
     * @param delay Time to wait in seconds
     */
    public static void exec(String command, int delay) {
        try {
            if (command == null || command.isEmpty()) {
                logger.error("COMMAND IS NULL OR EMPTY!");
                return;
            }

            int i = delay;
            int sleep;

            while (0 < i) {
                // If "i" is power of 2
                if ((i != 0) && ((i & (i - 1)) == 0)) {
                    logger.info("running command in {} seconds", i);
                    sleep = i == 1 ? 1 : i / 2;
                    i /= 2;
                } else {
                    sleep = 1;
                    i--;
                }

                Thread.sleep(sleep * 1000L);
            }

            exec(command);
        } catch (Exception e) {
            logger.error("running command failed" , e);
        }
    }

    /**
     * Execute the command immediately.
     * @param command Command to execute
     */
    public static void exec(String command) {
        try {
            if (command == null || command.isEmpty()) {
                logger.error("COMMAND IS NULL OR EMPTY!");
                return;
            }

            boolean isWindows = System.getProperty("os.name").toLowerCase().startsWith("windows");
            ProcessBuilder builder = new ProcessBuilder();

            if (isWindows) {
                logger.info("running command on windows={}", command);
                builder.command("cmd.exe", "/c", command);
            } else {
                logger.info("running command on linux={}", command);
                builder.command("sh", "-c", command);
            }

            builder.directory(new File(System.getProperty("user.home")));
            Process process = builder.start();
            ExecutorService executor = Executors.newSingleThreadExecutor();

            executor.submit(() ->
                    new BufferedReader(new InputStreamReader(process.getInputStream())).lines()
                            .forEach(System.out::println)
            );

            logger.info("running command done");
            int exitCode = process.waitFor();
            assert exitCode == 0;
        } catch (Exception e) {
            logger.error("running command failed" , e);
        }
    }
}