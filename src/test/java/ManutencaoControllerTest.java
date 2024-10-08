// import java.time.LocalDate;
// import java.util.Arrays;
// import java.util.List;

// import static org.junit.jupiter.api.Assertions.assertEquals;
// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;
// import static org.mockito.Mockito.mock;
// import static org.mockito.Mockito.verify;
// import static org.mockito.Mockito.when;

// import com.manutencao.connection.ManutencaoDAO;
// import com.manutencao.controller.ManutencaoController;
// import com.manutencao.model.Manutencao;

// public class ManutencaoControllerTest {
//     // Mock da interface ManutencaoDAO para simular a interação com o banco de dados
//     private ManutencaoDAO manutencaoDAOMock;
//     // Instância do controlador de manutenção a ser testada
//     private ManutencaoController manutencaoController;

//     // Método que configura o ambiente de testes antes da execução de cada teste
//     @BeforeEach
//     public void setUp() {
//         // Cria um mock da classe ManutencaoDAO
//         manutencaoDAOMock = mock(ManutencaoDAO.class);
//         // Cria uma instância do ManutencaoController usando o mock
//         manutencaoController = new ManutencaoController() {
//             public ManutencaoDAO getManutencaoDAO() {
//                 return manutencaoDAOMock;
//             }
//         };
//     }

//     // Testa a funcionalidade de salvar uma manutenção
//     @Test
//     public void testSalvarManutencao() {
//         // Cria uma nova manutenção para teste
//         Manutencao manutencao = new Manutencao(1, 1, 1, "Tipo", "Descrição", LocalDate.now(), "Pendente", "Peças", 5);
        
//         // Chama o método salvarManutencao do controlador
//         manutencaoController.salvarManutencao(manutencao);
        
//         // Verifica se o método salvar do DAO foi chamado com a manutenção correta
//         verify(manutencaoDAOMock).salvar(manutencao);
//     }

//     // Testa a funcionalidade de atualizar uma manutenção
//     @Test
//     public void testAtualizarManutencao() {
//         // Cria uma nova manutenção para teste
//         Manutencao manutencao = new Manutencao(1, 1, 1, "Tipo", "Descrição", LocalDate.now(), "Pendente", "Peças", 5);

//         // Chama o método atualizarManutencao do controlador
//         manutencaoController.atualizarManutencao(manutencao);
        
//         // Verifica se o método atualizar do DAO foi chamado com a manutenção correta
//         verify(manutencaoDAOMock).atualizar(manutencao);
//     }

//     // Testa a funcionalidade de excluir uma manutenção
//     @Test
//     public void testExcluirManutencao() {
//         int id = 1; // ID da manutenção a ser excluída
        
//         // Chama o método excluirManutencao do controlador
//         manutencaoController.excluirManutencao(id);
        
//         // Verifica se o método deletar do DAO foi chamado com o ID correto
//         verify(manutencaoDAOMock).deletar(id);
//     }

//     // Testa a busca de uma manutenção por ID
//     @Test
//     public void testBuscarManutencaoPorId() {
//         int id = 1; // ID da manutenção a ser buscada
//         // Cria uma nova manutenção para teste
//         Manutencao manutencao = new Manutencao(1, 1, 1, "Tipo", "Descrição", LocalDate.now(), "Pendente", "Peças", 5);

//         // Simula o retorno do DAO quando buscarPorId é chamado
//         when(manutencaoDAOMock.buscarPorId(id)).thenReturn(manutencao);

//         // Chama o método buscarManutencaoPorId do controlador
//         Manutencao resultado = manutencaoController.buscarManutencaoPorId(id);
        
//         // Verifica se o resultado é igual à manutenção esperada
//         assertEquals(manutencao, resultado);
//         // Verifica se o método buscarPorId do DAO foi chamado com o ID correto
//         verify(manutencaoDAOMock).buscarPorId(id);
//     }

//     // Testa a listagem de todas as manutenções
//     @Test
//     public void testListarManutencoes() {
//         // Cria duas manutenções para teste
//         Manutencao manutencao1 = new Manutencao(1, 1, 1, "Tipo 1", "Descrição 1", LocalDate.now(), "Pendente", "Peças 1", 5);
//         Manutencao manutencao2 = new Manutencao(2, 2, 2, "Tipo 2", "Descrição 2", LocalDate.now(), "Concluída", "Peças 2", 10);

//         // Cria uma lista com as manutenções
//         List<Manutencao> manutencoes = Arrays.asList(manutencao1, manutencao2);

//         // Simula o retorno do DAO quando listarTodos é chamado
//         when(manutencaoDAOMock.listarTodos()).thenReturn(manutencoes);

//         // Chama o método listarManutencoes do controlador
//         List<Manutencao> resultado = manutencaoController.listarManutencoes();
        
//         // Verifica se o resultado é igual à lista esperada
//         assertEquals(manutencoes, resultado);
//         // Verifica se o método listarTodos do DAO foi chamado
//         verify(manutencaoDAOMock).listarTodos();
//     }

//     // Testa a funcionalidade de marcar uma manutenção como concluída
//     @Test
//     public void testMarcarComoConcluida() {
//         int id = 1; // ID da manutenção a ser marcada
//         // Cria uma nova manutenção para teste
//         Manutencao manutencao = new Manutencao(1, 1, 1, "Tipo", "Descrição", LocalDate.now(), "Pendente", "Peças", 5);

//         // Simula o retorno do DAO quando buscarPorId é chamado
//         when(manutencaoDAOMock.buscarPorId(id)).thenReturn(manutencao);

//         // Chama o método marcarComoConcluida do controlador
//         manutencaoController.marcarComoConcluida(id);

//         // Verifica se os métodos buscarPorId e atualizar do DAO foram chamados corretamente
//         verify(manutencaoDAOMock).buscarPorId(id);
//         verify(manutencaoDAOMock).atualizar(manutencao);
//         // Verifica se o status da manutenção foi alterado para "Concluída"
//         assertEquals("Concluída", manutencao.getStatus());
//     }

//     // Testa a funcionalidade de marcar uma manutenção como pendente
//     @Test
//     public void testMarcarComoPendente() {
//         int id = 1; // ID da manutenção a ser marcada
//         // Cria uma nova manutenção para teste
//         Manutencao manutencao = new Manutencao(1, 1, 1, "Tipo", "Descrição", LocalDate.now(), "Concluída", "Peças", 5);

//         // Simula o retorno do DAO quando buscarPorId é chamado
//         when(manutencaoDAOMock.buscarPorId(id)).thenReturn(manutencao);

//         // Chama o método marcarComoPendente do controlador
//         manutencaoController.marcarComoPendente(id);

//         // Verifica se os métodos buscarPorId e atualizar do DAO foram chamados corretamente
//         verify(manutencaoDAOMock).buscarPorId(id);
//         verify(manutencaoDAOMock).atualizar(manutencao);
//         // Verifica se o status da manutenção foi alterado para "Pendente"
//         assertEquals("Pendente", manutencao.getStatus());
//     }

//     // Testa a busca de manutenções por ID de equipamento
//     @Test
//     public void testBuscarManutencoesPorEquipamentoId() {
//         int equipamentoId = 1; // ID do equipamento
//         // Cria duas manutenções para teste
//         Manutencao manutencao1 = new Manutencao(1, equipamentoId, 1, "Tipo 1", "Descrição 1", LocalDate.now(), "Pendente", "Peças 1", 5);
//         Manutencao manutencao2 = new Manutencao(2, equipamentoId, 2, "Tipo 2", "Descrição 2", LocalDate.now(), "Concluída", "Peças 2", 10);

//         // Cria uma lista com as manutenções
//         List<Manutencao> manutencoes = Arrays.asList(manutencao1, manutencao2);

//         // Simula o retorno do DAO quando listarPorEquipamento é chamado
//         when(manutencaoDAOMock.listarPorEquipamento(equipamentoId)).thenReturn(manutencoes);

//         // Chama o método buscarManutencoesPorEquipamentoId do controlador
//         List<Manutencao> resultado = manutencaoController.buscarManutencoesPorEquipamentoId(equipamentoId);
        
//         // Verifica se o resultado é igual à lista esperada
//         assertEquals(manutencoes, resultado);
//         // Verifica se o método listarPorEquipamento do DAO foi chamado
//         verify(manutencaoDAOMock).listarPorEquipamento(equipamentoId);
//     }

//     // Testa a funcionalidade de gerar relatório de manutenções
//     @Test
//     public void testGerarRelatorioManutencoes() {
//         // Cria duas manutenções para teste
//         Manutencao manutencao1 = new Manutencao(1, 1, 1, "Tipo 1", "Descrição 1", LocalDate.now(), "Pendente", "Peças 1", 5);
//         Manutencao manutencao2 = new Manutencao(2, 2, 2, "Tipo 2", "Descrição 2", LocalDate.now(), "Concluída", "Peças 2", 10);

//         // Cria uma lista com as manutenções
//         List<Manutencao> manutencoes = Arrays.asList(manutencao1, manutencao2);

//         // Simula o retorno do DAO quando listarTodos é chamado
//         when(manutencaoDAOMock.listarTodos()).thenReturn(manutencoes);

//         // Chama o método gerarRelatorioManutencoes do controlador
//         manutencaoController.gerarRelatorioManutencoes();

//         // Verifica se o método listarTodos do DAO foi chamado
//         verify(manutencaoDAOMock).listarTodos();
//         // Adicione asserções ou verificações adicionais conforme necessário para validar o relatório
//     }
// }
