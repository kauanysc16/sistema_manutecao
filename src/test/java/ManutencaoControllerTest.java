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

import com.manutencao.connection.ManutencaoDAO;
import com.manutencao.controller.ManutencaoController;
import com.manutencao.model.Manutencao;

public class ManutencaoControllerTest {
    private ManutencaoDAO manutencaoDAOMock;
    private ManutencaoController manutencaoController;

    @BeforeEach
    public void setUp() {
        manutencaoDAOMock = mock(ManutencaoDAO.class);
        manutencaoController = new ManutencaoController() {
            public ManutencaoDAO getManutencaoDAO() {
                return manutencaoDAOMock;
            }
        };
    }

    @Test
    public void testSalvarManutencao() throws SQLException {
        Manutencao manutencao = new Manutencao(1, 1, 1, "Tipo", "Descrição", LocalDate.now(), "Pendente", "Peças", 5);
        
        manutencaoController.salvarManutencao(manutencao);
        
        verify(manutencaoDAOMock).salvar(manutencao);
    }

    @Test
    public void testAtualizarManutencao() throws SQLException {
        Manutencao manutencao = new Manutencao(1, 1, 1, "Tipo", "Descrição", LocalDate.now(), "Pendente", "Peças", 5);

        manutencaoController.atualizarManutencao(manutencao);
        
        verify(manutencaoDAOMock).atualizar(manutencao);
    }

    @Test
    public void testExcluirManutencao() throws SQLException {
        int id = 1;
        
        manutencaoController.excluirManutencao(id);
        
        verify(manutencaoDAOMock).deletar(id);
    }

    @Test
    public void testBuscarManutencaoPorId() throws SQLException {
        int id = 1;
        Manutencao manutencao = new Manutencao(1, 1, 1, "Tipo", "Descrição", LocalDate.now(), "Pendente", "Peças", 5);

        when(manutencaoDAOMock.buscarPorId(id)).thenReturn(manutencao);

        Manutencao resultado = manutencaoController.buscarManutencaoPorId(id);
        
        assertEquals(manutencao, resultado);
        verify(manutencaoDAOMock).buscarPorId(id);
    }

    @Test
    public void testListarManutencoes() throws SQLException {
        Manutencao manutencao1 = new Manutencao(1, 1, 1, "Tipo 1", "Descrição 1", LocalDate.now(), "Pendente", "Peças 1", 5);
        Manutencao manutencao2 = new Manutencao(2, 2, 2, "Tipo 2", "Descrição 2", LocalDate.now(), "Concluída", "Peças 2", 10);

        List<Manutencao> manutencoes = Arrays.asList(manutencao1, manutencao2);

        when(manutencaoDAOMock.listarTodos()).thenReturn(manutencoes);

        List<Manutencao> resultado = manutencaoController.listarManutencoes();
        
        assertEquals(manutencoes, resultado);
        verify(manutencaoDAOMock).listarTodos();
    }

    @Test
    public void testMarcarComoConcluida() throws SQLException {
        int id = 1;
        Manutencao manutencao = new Manutencao(1, 1, 1, "Tipo", "Descrição", LocalDate.now(), "Pendente", "Peças", 5);

        when(manutencaoDAOMock.buscarPorId(id)).thenReturn(manutencao);

        manutencaoController.marcarComoConcluida(id);

        verify(manutencaoDAOMock).buscarPorId(id);
        verify(manutencaoDAOMock).atualizar(manutencao);
        assertEquals("Concluída", manutencao.getStatus());
    }

    @Test
    public void testMarcarComoPendente() throws SQLException {
        int id = 1;
        Manutencao manutencao = new Manutencao(1, 1, 1, "Tipo", "Descrição", LocalDate.now(), "Concluída", "Peças", 5);

        when(manutencaoDAOMock.buscarPorId(id)).thenReturn(manutencao);

        manutencaoController.marcarComoPendente(id);

        verify(manutencaoDAOMock).buscarPorId(id);
        verify(manutencaoDAOMock).atualizar(manutencao);
        assertEquals("Pendente", manutencao.getStatus());
    }

    @Test
    public void testBuscarManutencoesPorEquipamentoId() throws SQLException {
        int equipamentoId = 1;
        Manutencao manutencao1 = new Manutencao(1, equipamentoId, 1, "Tipo 1", "Descrição 1", LocalDate.now(), "Pendente", "Peças 1", 5);
        Manutencao manutencao2 = new Manutencao(2, equipamentoId, 2, "Tipo 2", "Descrição 2", LocalDate.now(), "Concluída", "Peças 2", 10);

        List<Manutencao> manutencoes = Arrays.asList(manutencao1, manutencao2);

        when(manutencaoDAOMock.listarPorEquipamento(equipamentoId)).thenReturn(manutencoes);

        List<Manutencao> resultado = manutencaoController.buscarManutencoesPoridEquipamento(equipamentoId);
        
        assertEquals(manutencoes, resultado);
        verify(manutencaoDAOMock).listarPorEquipamento(equipamentoId);
    }

    @Test
    public void testGerarRelatorioManutencoes() throws SQLException {
        Manutencao manutencao1 = new Manutencao(1, 1, 1, "Tipo 1", "Descrição 1", LocalDate.now(), "Pendente", "Peças 1", 5);
        Manutencao manutencao2 = new Manutencao(2, 2, 2, "Tipo 2", "Descrição 2", LocalDate.now(), "Concluída", "Peças 2", 10);

        List<Manutencao> manutencoes = Arrays.asList(manutencao1, manutencao2);

        when(manutencaoDAOMock.listarTodos()).thenReturn(manutencoes);

        manutencaoController.gerarRelatorioManutencoes();

        verify(manutencaoDAOMock).listarTodos();
    }
}
