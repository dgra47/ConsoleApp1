package com.consoleapp1;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import com.consoleapp1.interfaces.ConsloeUserInterface;
import com.consoleapp1.service.EmbeddedDbService;
import com.consoleapp1.service.PropertiesService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ConsoleTodo {

    public static void main(String[] args) {
        PropertiesService propertiesService = new PropertiesService();
        EmbeddedDbService dbService = new EmbeddedDbService(propertiesService);
        try (Connection connection = dbService.setupDatabase()) {
            ConsloeUserInterface consoleInterface = new ConsloeUserInterface(connection);
            consoleInterface.execute();
        } catch (SQLException e) {
            log.error("Cannot establish db connection", e);
        } catch (IOException e) {
            log.error("Cannot read file for db connection init", e);
        }
    }
}
