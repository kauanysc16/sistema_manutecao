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
    private TecnicoDAO tecnicoDAOMock;
    private TecnicoController tecnicoController;

    @BeforeEach
    public void setUp() {
        tecnicoDAOMock = mock(TecnicoDAO.class);
        tecnicoController = new TecnicoController() {
            public TecnicoDAO getTecnicoDAO() {
                return tecnicoDAOMock;
            }
        };
    }

    @Test
    public void testSalvarTecnico() {
        Tecnico tecnico = new Tecnico(1, "João", "Eletricista", true);
        
        tecnicoController.salvarTecnico(tecnico);
        
        verify(tecnicoDAOMock).salvar(tecnico);
    }

    @Test
    public void testAtualizarTecnico() {
        Tecnico tecnico = new Tecnico(1, "João", "Eletricista", true);

        tecnicoController.atualizarTecnico(tecnico);
        
        verify(tecnicoDAOMock).atualizar(tecnico);
    }

    @Test
    public void testDeletarTecnico() {
        int id = 1;
        
        tecnicoController.deletarTecnico(id);
        
        verify(tecnicoDAOMock).deletar(id);
    }

    @Test
    public void testBuscarTecnicoPorId() {
        int id = 1;
        Tecnico tecnico = new Tecnico(1, "João", "Eletricista", true);

        when(tecnicoDAOMock.buscarPorId(id)).thenReturn(tecnico);

        Tecnico resultado = tecnicoController.buscarTecnicoPorId(id);
        
        assertEquals(tecnico, resultado);
        verify(tecnicoDAOMock).buscarPorId(id);
    }

    @Test
    public void testListarTodosTecnicos() {
        Tecnico tecnico1 = new Tecnico(1, "João", "Eletricista", true);
        Tecnico tecnico2 = new Tecnico(2, "Maria", "Mecânica", false);

        List<Tecnico> tecnicos = Arrays.asList(tecnico1, tecnico2);

        when(tecnicoDAOMock.listarTodos()).thenReturn(tecnicos);

        List<Tecnico> resultado = tecnicoController.listarTodosTecnicos();
        
        assertEquals(tecnicos, resultado);
        verify(tecnicoDAOMock).listarTodos();
    }

    @Test
    public void testBuscarTecnicosPorEspecialidade() {
        String especialidade = "Eletricista";
        Tecnico tecnico1 = new Tecnico(1, "João", especialidade, true);
        Tecnico tecnico2 = new Tecnico(2, "Carlos", especialidade, true);

        List<Tecnico> tecnicos = Arrays.asList(tecnico1, tecnico2);

        when(tecnicoDAOMock.buscarPorEspecialidade(especialidade)).thenReturn(tecnicos);

        List<Tecnico> resultado = tecnicoController.buscarTecnicosPorEspecialidade(especialidade);
        
        assertEquals(tecnicos, resultado);
        verify(tecnicoDAOMock).buscarPorEspecialidade(especialidade);
    }

    @Test
    public void testVerificarDisponibilidade() {
        Tecnico tecnico1 = new Tecnico(1, "João", "Eletricista", true);
        Tecnico tecnico2 = new Tecnico(2, "Maria", "Mecânica", true);

        List<Tecnico> tecnicos = Arrays.asList(tecnico1, tecnico2);

        when(tecnicoDAOMock.verificarDisponibilidade()).thenReturn(tecnicos);

        List<Tecnico> resultado = tecnicoController.verificarDisponibilidade();
        
        assertEquals(tecnicos, resultado);
        verify(tecnicoDAOMock).verificarDisponibilidade();
    }
}
