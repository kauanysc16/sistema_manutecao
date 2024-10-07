import java.util.Arrays; // Importa a classe Arrays da biblioteca java.util para manipulação de arrays
import java.util.List; // Importa a classe List da biblioteca java.util para manipulação de listas

import static org.junit.jupiter.api.Assertions.assertEquals; // Importa o método assertEquals da biblioteca JUnit para asserções em testes
import org.junit.jupiter.api.BeforeEach; // Importa a anotação BeforeEach da biblioteca JUnit para configuração antes de cada teste
import org.junit.jupiter.api.Test; // Importa a anotação Test da biblioteca JUnit para definição de métodos de teste
import static org.mockito.Mockito.mock; // Importa o método mock da biblioteca Mockito para criação de mocks
import static org.mockito.Mockito.verify; // Importa o método verify da biblioteca Mockito para verificação de interações
import static org.mockito.Mockito.when; // Importa o método when da biblioteca Mockito para definição de comportamento de mocks

import com.manutencao.connection.EquipamentoDAO; // Importa a classe EquipamentoDAO para operações de banco de dados
import com.manutencao.controller.EquipamentoController; // Importa a classe EquipamentoController para controle de lógica de equipamentos
import com.manutencao.model.Equipamento; // Importa a classe Equipamento do modelo

public class EquipamentoControllerTest {
    private EquipamentoDAO equipamentoDAOMock; // Mock do DAO para simular operações de banco de dados
    private EquipamentoController equipamentoController; // Instância do controlador de equipamentos

    @BeforeEach
    public void setUp() {
        equipamentoDAOMock = mock(EquipamentoDAO.class); // Criação do mock do DAO
        equipamentoController = new EquipamentoController() {
            // Sobrescreve o método getEquipamentoDAO para retornar o mock
            public EquipamentoDAO getEquipamentoDAO() {
                return equipamentoDAOMock; // Substituição do DAO no controlador
            }
        };
    }

    @Test
    public void testSalvarEquipamento() {
        // Criação de um novo equipamento
        Equipamento equipamento = new Equipamento("Aparelho 1", "Modelo 1", "Local 1");

        equipamentoController.salvarEquipamento(equipamento); // Chamada ao método a ser testado

        // Verifica se o método salvar foi chamado no DAO
        verify(equipamentoDAOMock).salvar(equipamento);
    }

    @Test
    public void testAtualizarEquipamento() {
        // Criação de um equipamento existente
        Equipamento equipamento = new Equipamento("Aparelho 1", "Modelo 1", "Local 1");

        equipamentoController.atualizarEquipamento(equipamento); // Chamada ao método a ser testado

        // Verifica se o método atualizar foi chamado no DAO
        verify(equipamentoDAOMock).atualizar(equipamento);
    }

    @Test
    public void testDeletarEquipamento() {
        int id = 1; // ID do equipamento a ser deletado

        equipamentoController.deletarEquipamento(id); // Chamada ao método a ser testado

        // Verifica se o método deletar foi chamado no DAO
        verify(equipamentoDAOMock).deletar(id);
    }

    @Test
    public void testBuscarEquipamentoPorId() {
        int id = 1; // ID do equipamento a ser buscado
        // Criação de um equipamento esperado
        Equipamento equipamento = new Equipamento("Aparelho 1", "Modelo 1", "Local 1");

        // Configuração do comportamento do mock para retornar o equipamento esperado
        when(equipamentoDAOMock.buscarPorId(id)).thenReturn(equipamento);

        Equipamento resultado = equipamentoController.buscarEquipamentoPorId(id); // Chamada ao método a ser testado

        // Verifica se o resultado é o esperado
        assertEquals(equipamento, resultado);
        // Verifica se o método buscarPorId foi chamado no DAO
        verify(equipamentoDAOMock).buscarPorId(id);
    }

    @Test
    public void testListarTodosEquipamentos() {
        // Criação de equipamentos existentes
        Equipamento equipamento1 = new Equipamento("Aparelho 1", "Modelo 1", "Local 1");
        Equipamento equipamento2 = new Equipamento("Aparelho 2", "Modelo 2", "Local 2");

        // Lista de equipamentos esperados
        List<Equipamento> equipamentos = Arrays.asList(equipamento1, equipamento2);

        // Configuração do comportamento do mock para retornar a lista de equipamentos
        // esperados
        when(equipamentoDAOMock.listarTodos()).thenReturn(equipamentos);

        List<Equipamento> resultado = equipamentoController.listarTodosEquipamentos(); // Chamada ao método a ser
                                                                                       // testado

        // Verifica se o resultado é o esperado
        assertEquals(equipamentos, resultado);
        // Verifica se o método listarTodos foi chamado no DAO
        verify(equipamentoDAOMock).listarTodos();
    }
}
