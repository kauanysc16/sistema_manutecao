import java.time.LocalDateTime; // Importa a classe LocalDateTime da biblioteca java.time para manipulação de datas e horas
import java.util.Arrays; // Importa a classe Arrays da biblioteca java.util para manipulação de arrays
import java.util.List; // Importa a classe List da biblioteca java.util para manipulação de listas

import static org.junit.jupiter.api.Assertions.assertEquals; // Importa o método assertEquals da biblioteca JUnit para asserções em testes
import org.junit.jupiter.api.BeforeEach; // Importa a anotação BeforeEach da biblioteca JUnit para configuração antes de cada teste
import org.junit.jupiter.api.Test; // Importa a anotação Test da biblioteca JUnit para definição de métodos de teste
import static org.mockito.Mockito.mock; // Importa o método mock da biblioteca Mockito para criação de mocks
import static org.mockito.Mockito.verify; // Importa o método verify da biblioteca Mockito para verificação de interações
import static org.mockito.Mockito.when; // Importa o método when da biblioteca Mockito para definição de comportamento de mocks

import com.manutencao.connection.ManutencaoDAO; // Importa a classe ManutencaoDAO para operações de banco de dados
import com.manutencao.controller.ManutencaoController; // Importa a classe ManutencaoController para controle de lógica de manutenção
import com.manutencao.model.Manutencao; // Importa a classe Manutencao do modelo

public class ManutencaoControllerTest {
    private ManutencaoDAO manutencaoDAOMock; // Mock do DAO para simular operações de banco de dados
    private ManutencaoController manutencaoController; // Instância do controlador de manutenção

    @BeforeEach
    public void setUp() {
        manutencaoDAOMock = mock(ManutencaoDAO.class); // Criação do mock do DAO
        manutencaoController = new ManutencaoController() {
            // Sobrescreve o método getManutencaoDAO para retornar o mock
            public ManutencaoDAO getManutencaoDAO() {
                return manutencaoDAOMock; // Substituição do DAO no controlador
            }
        };
    }

    @Test
    public void testSalvarManutencao() {
        // Criação de uma nova manutenção com dados de exemplo
        Manutencao manutencao = new Manutencao(1, 2, 3, LocalDateTime.now(), "Teste", "Pendente");

        // Chamada ao método a ser testado
        manutencaoController.salvarManutencao(manutencao);

        // Verifica se o método salvar foi chamado no DAO
        verify(manutencaoDAOMock).salvar(manutencao);
    }

    @Test
    public void testAtualizarManutencao() {
        // Criação de uma manutenção existente com dados de exemplo
        Manutencao manutencao = new Manutencao(1, 2, 3, LocalDateTime.now(), "Teste", "Pendente");

        // Chamada ao método a ser testado
        manutencaoController.atualizarManutencao(manutencao);

        // Verifica se o método atualizar foi chamado no DAO
        verify(manutencaoDAOMock).atualizar(manutencao);
    }

    @Test
    public void testExcluirManutencao() {
        int id = 1; // ID da manutenção a ser excluída

        // Chamada ao método a ser testado
        manutencaoController.excluirManutencao(id);

        // Verifica se o método deletar foi chamado no DAO
        verify(manutencaoDAOMock).deletar(id);
    }

    @Test
    public void testBuscarManutencaoPorId() {
        int id = 1; // ID da manutenção a ser buscada
        // Criação de uma manutenção esperada com dados de exemplo
        Manutencao manutencao = new Manutencao(id, 2, 3, LocalDateTime.now(), "Teste", "Pendente");
        // Configuração do comportamento do mock para retornar a manutenção esperada
        when(manutencaoDAOMock.buscarPorId(id)).thenReturn(manutencao);

        // Chamada ao método a ser testado
        Manutencao resultado = manutencaoController.buscarManutencaoPorId(id);

        // Verifica se o resultado é o esperado
        assertEquals(manutencao, resultado);
        // Verifica se o método buscarPorId foi chamado no DAO
        verify(manutencaoDAOMock).buscarPorId(id);
    }

    @Test
    public void testListarManutencoes() {
        // Criação de manutenções com dados de exemplo
        Manutencao manutencao1 = new Manutencao(1, 2, 3, LocalDateTime.now(), "Teste 1", "Pendente");
        Manutencao manutencao2 = new Manutencao(2, 3, 4, LocalDateTime.now(), "Teste 2", "Concluída");
        // Lista de manutenções esperadas
        List<Manutencao> manutencoes = Arrays.asList(manutencao1, manutencao2);
        // Configuração do comportamento do mock para retornar a lista de manutenções
        when(manutencaoDAOMock.listarTodos()).thenReturn(manutencoes);

        // Chamada ao método a ser testado
        List<Manutencao> resultado = manutencaoController.listarManutencoes();

        // Verifica se o resultado é o esperado
        assertEquals(manutencoes, resultado);
        // Verifica se o método listarTodos foi chamado no DAO
        verify(manutencaoDAOMock).listarTodos();
    }

    @Test
    public void testMarcarComoConcluida() {
        int id = 1; // ID da manutenção a ser marcada como concluída
        // Criação de uma manutenção existente
        Manutencao manutencao = new Manutencao(id, 2, 3, LocalDateTime.now(), "Teste", "Pendente");
        // Configuração do comportamento do mock para retornar a manutenção
        when(manutencaoDAOMock.buscarPorId(id)).thenReturn(manutencao);

        // Chamada ao método a ser testado
        manutencaoController.marcarComoConcluida(id);

        // Verifica se o método atualizar foi chamado no DAO
        verify(manutencaoDAOMock).atualizar(manutencao);
        // Verifica se o status foi atualizado corretamente
        assertEquals("Concluída", manutencao.getStatus());
    }

    @Test
    public void testMarcarComoPendente() {
        int id = 1; // ID da manutenção a ser marcada como pendente
        // Criação de uma manutenção existente com status "Concluída"
        Manutencao manutencao = new Manutencao(id, 2, 3, LocalDateTime.now(), "Teste", "Concluída");
        // Configuração do comportamento do mock para retornar a manutenção
        when(manutencaoDAOMock.buscarPorId(id)).thenReturn(manutencao);

        // Chamada ao método a ser testado
        manutencaoController.marcarComoPendente(id);

        // Verifica se o método atualizar foi chamado no DAO
        verify(manutencaoDAOMock).atualizar(manutencao);
        // Verifica se o status foi atualizado corretamente
        assertEquals("Pendente", manutencao.getStatus());
    }
}
