package com.example.system;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import static org.junit.jupiter.api.Assertions.*;

public class ClienteSystemTest {

    @Test
    public void testCadastroCliente() {
        // Configuração do WebDriver com o caminho correto do ChromeDriver
        System.setProperty("webdriver.chrome.driver", "C:\\drivers\\chromedriver-win64\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        // Navegar para a página de cadastro
        driver.get("http://localhost:8080/clientes");

        // Preencher o formulário de cadastro
        driver.findElement(By.id("nome")).sendKeys("Lucas");
        driver.findElement(By.id("email")).sendKeys("lucas@email.com");
        driver.findElement(By.id("telefone")).sendKeys("123456789");
        driver.findElement(By.id("endereco")).sendKeys("Rua A");

        // Submeter o formulário
        driver.findElement(By.id("submit")).click();

        // Verificar se o cadastro foi realizado com sucesso
        String mensagem = driver.findElement(By.id("mensagemSucesso")).getText();
        assertEquals("Cliente cadastrado com sucesso", mensagem);

        // Fechar o navegador
        driver.quit();
    }
}
