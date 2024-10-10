import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.manutencao.connection.TecnicoDAO;
import com.manutencao.controller.TecnicoController;
import com.manutencao.model.Tecnico;

public class TecnicoControllerTest {
    // Mock da interface TecnicoDAO para simular a interação com o banco de dados
    private TecnicoDAO tecnicoDAOMock;
    // Instância do controlador de técnicos a ser testada
    private TecnicoController tecnicoController;

    // Método que configura o ambiente de testes antes da execução de cada teste
    @BeforeEach
    public void setUp() {
        // Cria um mock da classe TecnicoDAO
        tecnicoDAOMock = mock(TecnicoDAO.class);
        // Cria uma instância do TecnicoController usando o mock
        tecnicoController = new TecnicoController() {
            public TecnicoDAO getTecnicoDAO() {
                return tecnicoDAOMock;
            }
        };
    }

    // Testa a funcionalidade de salvar um técnico
    @Test
    public void testSalvarTecnico() {
        // Cria um novo técnico para teste
        Tecnico tecnico = new Tecnico(1, "João", "Eletricista", true);
        
        // Chama o método salvarTecnico do controlador
        tecnicoController.salvarTecnico(tecnico);
        
        // Verifica se o método salvar do DAO foi chamado com o técnico correto
        verify(tecnicoDAOMock).salvar(tecnico);
    }

    // Testa a funcionalidade de atualizar um técnico
    @Test
    public void testAtualizarTecnico() {
        // Cria um novo técnico para teste
        Tecnico tecnico = new Tecnico(1, "João", "Eletricista", true);

        // Chama o método atualizarTecnico do controlador
        tecnicoController.atualizarTecnico(tecnico);
        
        // Verifica se o método atualizar do DAO foi chamado com o técnico correto
        verify(tecnicoDAOMock).atualizar(tecnico);
    }

    // Testa a funcionalidade de deletar um técnico
    @Test
    public void testDeletarTecnico() {
        int id = 1; // ID do técnico a ser deletado
        
        // Chama o método deletarTecnico do controlador
        tecnicoController.deletarTecnico(id);
        
        // Verifica se o método deletar do DAO foi chamado com o ID correto
        verify(tecnicoDAOMock).deletar(id);
    }

    // Testa a busca de um técnico por ID
    @Test
    public void testBuscarTecnicoPorId() {
        int id = 1; // ID do técnico a ser buscado
        // Cria um novo técnico para teste
        Tecnico tecnico = new Tecnico(1, "João", "Eletricista", true);

        // Simula o retorno do DAO quando buscarPorId é chamado
        when(tecnicoDAOMock.buscarPorId(id)).thenReturn(tecnico);

        // Chama o método buscarTecnicoPorId do controlador
        Tecnico resultado = tecnicoController.buscarTecnicoPorId(id);
        
        // Verifica se o resultado é igual ao técnico esperado
        assertEquals(tecnico, resultado);
        // Verifica se o método buscarPorId do DAO foi chamado com o ID correto
        verify(tecnicoDAOMock).buscarPorId(id);
    }

    // Testa a listagem de todos os técnicos
    @Test
    public void testListarTodosTecnicos() {
        // Cria dois técnicos para teste
        Tecnico tecnico1 = new Tecnico(1, "João", "Eletricista", true);
        Tecnico tecnico2 = new Tecnico(2, "Maria", "Mecânica", false);

        // Cria uma lista com os técnicos
        List<Tecnico> tecnicos = Arrays.asList(tecnico1, tecnico2);

        // Simula o retorno do DAO quando listarTodos é chamado
        when(tecnicoDAOMock.listarTodos()).thenReturn(tecnicos);

        // Chama o método listarTodosTecnicos do controlador
        List<Tecnico> resultado = tecnicoController.listarTodosTecnicos();
        
        // Verifica se o resultado é igual à lista esperada
        assertEquals(tecnicos, resultado);
        // Verifica se o método listarTodos do DAO foi chamado
        verify(tecnicoDAOMock).listarTodos();
    }

    // Testa a busca de técnicos por especialidade
    @Test
    public void testBuscarTecnicosPorEspecialidade() {
        String especialidade = "Eletricista"; // Especialidade a ser buscada
        // Cria dois técnicos para teste
        Tecnico tecnico1 = new Tecnico(1, "João", especialidade, true);
        Tecnico tecnico2 = new Tecnico(2, "Carlos", especialidade, true);

        // Cria uma lista com os técnicos
        List<Tecnico> tecnicos = Arrays.asList(tecnico1, tecnico2);

        // Simula o retorno do DAO quando buscarPorEspecialidade é chamado
        when(tecnicoDAOMock.buscarPorEspecialidade(especialidade)).thenReturn(tecnicos);

        // Chama o método buscarTecnicosPorEspecialidade do controlador
        List<Tecnico> resultado = tecnicoController.buscarTecnicosPorEspecialidade(especialidade);
        
        // Verifica se o resultado é igual à lista esperada
        assertEquals(tecnicos, resultado);
        // Verifica se o método buscarPorEspecialidade do DAO foi chamado
        verify(tecnicoDAOMock).buscarPorEspecialidade(especialidade);
    }

    // Testa a funcionalidade de verificar disponibilidade de técnicos
    @Test
    public void testVerificarDisponibilidade() {
        // Cria dois técnicos para teste
        Tecnico tecnico1 = new Tecnico(1, "João", "Eletricista", true);
        Tecnico tecnico2 = new Tecnico(2, "Maria", "Mecânica", true);

        // Cria uma lista com os técnicos
        List<Tecnico> tecnicos = Arrays.asList(tecnico1, tecnico2);

        // Simula o retorno do DAO quando verificarDisponibilidade é chamado
        when(tecnicoDAOMock.verificarDisponibilidade()).thenReturn(tecnicos);

        // Chama o método verificarDisponibilidade do controlador
        List<Tecnico> resultado = tecnicoController.verificarDisponibilidade();
        
        // Verifica se o resultado é igual à lista esperada
        assertEquals(tecnicos, resultado);
        // Verifica se o método verificarDisponibilidade do DAO foi chamado
        verify(tecnicoDAOMock).verificarDisponibilidade();
    }
}
