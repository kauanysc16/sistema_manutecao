import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.manutencao.connection.EquipamentoDAO;
import com.manutencao.controller.EquipamentoController;
import com.manutencao.model.Equipamento;

public class EquipamentoControllerTest {
    private EquipamentoDAO equipamentoDAOMock;
    private EquipamentoController equipamentoController;

    @BeforeEach
    public void setUp() {
        equipamentoDAOMock = mock(EquipamentoDAO.class);
        equipamentoController = new EquipamentoController() {
            public EquipamentoDAO getEquipamentoDAO() {
                return equipamentoDAOMock;
            }
        };
    }

    @Test
    public void testSalvarEquipamento() throws SQLException {
        Equipamento equipamento = new Equipamento(1, "Aparelho 1", "Modelo 1", "Local 1", "Especificações 1", LocalDate.now());
        
        boolean resultado = equipamentoController.salvarEquipamento(equipamento);
        
        verify(equipamentoDAOMock).salvar(equipamento);
        assertEquals(true, resultado);
    }

    @Test
    public void testAtualizarEquipamento() {
        Equipamento equipamento = new Equipamento(1, "Aparelho 1", "Modelo 1", "Local 1", "Especificações 1", LocalDate.now());

        boolean resultado = equipamentoController.atualizarEquipamento(equipamento);
        
        verify(equipamentoDAOMock).atualizar(equipamento);
        assertEquals(true, resultado);
    }

    @Test
    public void testDeletarEquipamento() {
        int id = 1;
        
        boolean resultado = equipamentoController.deletarEquipamento(id);
        
        verify(equipamentoDAOMock).deletar(id);
        assertEquals(true, resultado);
    }

    @Test
    public void testBuscarEquipamentoPorId() {
        int id = 1;
        Equipamento equipamento = new Equipamento(1, "Aparelho 1", "Modelo 1", "Local 1", "Especificações 1", LocalDate.now());

        when(equipamentoDAOMock.buscarPorId(id)).thenReturn(equipamento);

        Equipamento resultado = equipamentoController.buscarEquipamentoPorId(id);
        
        assertEquals(equipamento, resultado);
        verify(equipamentoDAOMock).buscarPorId(id);
    }

    @Test
    public void testListarTodosEquipamentos() {
        Equipamento equipamento1 = new Equipamento(1, "Aparelho 1", "Modelo 1", "Local 1", "Especificações 1", LocalDate.now());
        Equipamento equipamento2 = new Equipamento(2, "Aparelho 2", "Modelo 2", "Local 2", "Especificações 2", LocalDate.now());

        List<Equipamento> equipamentos = Arrays.asList(equipamento1, equipamento2);

        when(equipamentoDAOMock.listarTodos()).thenReturn(equipamentos);

        List<Equipamento> resultado = equipamentoController.listarTodosEquipamentos();
        
        assertEquals(equipamentos, resultado);
        verify(equipamentoDAOMock).listarTodos();
    }

    @Test
    public void testBuscarEquipamentoPorLocal() {
        String local = "Local 1";
        Equipamento equipamento1 = new Equipamento(1, "Aparelho 1", "Modelo 1", local, "Especificações 1", LocalDate.now());
        Equipamento equipamento2 = new Equipamento(2, "Aparelho 2", "Modelo 2", local, "Especificações 2", LocalDate.now());

        List<Equipamento> equipamentos = Arrays.asList(equipamento1, equipamento2);

        when(equipamentoDAOMock.buscarPorLocal(local)).thenReturn(equipamentos);

        List<Equipamento> resultado = equipamentoController.buscarEquipamentoPorLocal(local);
        
        assertEquals(equipamentos, resultado);
        verify(equipamentoDAOMock).buscarPorLocal(local);
    }
}
