import java.util.Arrays; // Importa a classe Arrays da biblioteca java.util para manipulação de arrays
import java.util.List; // Importa a classe List da biblioteca java.util para manipulação de listas

import static org.junit.jupiter.api.Assertions.assertEquals; // Importa o método assertEquals da biblioteca JUnit para asserções em testes
import org.junit.jupiter.api.BeforeEach; // Importa a anotação BeforeEach da biblioteca JUnit para configuração antes de cada teste
import org.junit.jupiter.api.Test; // Importa a anotação Test da biblioteca JUnit para definição de métodos de teste
import static org.mockito.Mockito.mock; // Importa o método mock da biblioteca Mockito para criação de mocks
import static org.mockito.Mockito.verify; // Importa o método verify da biblioteca Mockito para verificação de interações
import static org.mockito.Mockito.when; // Importa o método when da biblioteca Mockito para definição de comportamento de mocks

import com.manutencao.connection.TecnicoDAO; // Importa a classe TecnicoDAO para operações de banco de dados
import com.manutencao.controller.TecnicoController; // Importa a classe TecnicoController para controle de lógica de técnico
import com.manutencao.model.Tecnico; // Importa a classe Tecnico do modelo

public class TecnicoControllerTest {
    private TecnicoDAO tecnicoDAOMock; // Mock do DAO
    private TecnicoController tecnicoController; // Instância do controlador

    @BeforeEach
    public void setUp() {
        tecnicoDAOMock = mock(TecnicoDAO.class); // Criação do mock
        tecnicoController = new TecnicoController() {
            public TecnicoDAO getTecnicoDAO() {
                return tecnicoDAOMock; // Substituição do DAO no controlador
            }
        };
    }

    @Test
    public void testSalvarTecnico() {
        Tecnico tecnico = new Tecnico(1, "Tecnico 1", "Especialidade 1");

        tecnicoController.salvarTecnico(tecnico); // Chamada ao método a ser testado

        verify(tecnicoDAOMock).salvar(tecnico); // Verifica se o método salvar foi chamado no DAO
    }

    @Test
    public void testAtualizarTecnico() {
        Tecnico tecnico = new Tecnico(1, "Tecnico 1", "Especialidade 1");

        tecnicoController.atualizarTecnico(tecnico); // Chamada ao método a ser testado

        verify(tecnicoDAOMock).atualizar(tecnico); // Verifica se o método atualizar foi chamado no DAO
    }

    @Test
    public void testDeletarTecnico() {
        int id = 1;

        tecnicoController.deletarTecnico(id); // Chamada ao método a ser testado

        verify(tecnicoDAOMock).deletar(id); // Verifica se o método deletar foi chamado no DAO
    }

    @Test
    public void testBuscarTecnicoPorId() {
        int id = 1;
        Tecnico tecnico = new Tecnico(id, "Tecnico 1", "Especialidade 1");
        when(tecnicoDAOMock.buscarPorId(id)).thenReturn(tecnico); // Configuração do comportamento do mock

        Tecnico resultado = tecnicoController.buscarTecnicoPorId(id); // Chamada ao método a ser testado

        assertEquals(tecnico, resultado); // Verifica se o resultado é o esperado
        verify(tecnicoDAOMock).buscarPorId(id); // Verifica se o método buscarPorId foi chamado no DAO
    }

    @Test
    public void testListarTodosTecnicos() {
        Tecnico tecnico1 = new Tecnico(1, "Tecnico 1", "Especialidade 1");
        Tecnico tecnico2 = new Tecnico(2, "Tecnico 2", "Especialidade 2");
        List<Tecnico> tecnicos = Arrays.asList(tecnico1, tecnico2);
        when(tecnicoDAOMock.listarTodos()).thenReturn(tecnicos); // Configuração do comportamento do mock

        List<Tecnico> resultado = tecnicoController.listarTodosTecnicos(); // Chamada ao método a ser testado

        assertEquals(tecnicos, resultado); // Verifica se o resultado é o esperado
        verify(tecnicoDAOMock).listarTodos(); // Verifica se o método listarTodos foi chamado no DAO
    }
}
