// import static org.junit.jupiter.api.Assertions.*; // Importa as asserções do JUnit
// import static org.mockito.Mockito.*; // Importa métodos do Mockito
// import org.junit.jupiter.api.BeforeEach; // Importa a anotação para métodos que devem ser executados antes de cada teste
// import org.junit.jupiter.api.Test; // Importa a anotação para métodos de teste
// import java.time.LocalDate; // Importa a classe LocalDate para trabalhar com datas
// import java.util.Arrays; // Importa a classe Arrays para manipulação de arrays
// import java.util.List; // Importa a interface List para trabalhar com listas

// import com.manutencao.connection.EquipamentoDAO; // Importa a classe EquipamentoDAO
// import com.manutencao.model.Equipamento; // Importa a classe Equipamento
// import com.manutencao.controller.EquipamentoController; // Importa a classe EquipamentoController

// public class EquipamentoControllerTest { // Classe de teste para EquipamentoController
//     private EquipamentoDAO equipamentoDAOMock; // Declaração do objeto simulado para EquipamentoDAO
//     private EquipamentoController equipamentoController; // Declaração do controlador de equipamentos

//     @BeforeEach // Anotação que indica que o método deve ser executado antes de cada teste
//     public void setUp() { // Método de configuração do ambiente de teste
//         equipamentoDAOMock = mock(EquipamentoDAO.class); // Cria um objeto simulado para EquipamentoDAO
//         equipamentoController = new EquipamentoController() { // Cria uma instância de EquipamentoController com um método sobrescrito
//             public EquipamentoDAO getEquipamentoDAO() { // Sobrescreve o método para retornar o mock
//                 return equipamentoDAOMock; // Retorna o mock do EquipamentoDAO
//             }
//         };
//     }

//     @Test // Anotação que marca o método como um teste
//     public void testSalvarEquipamento() throws Exception { // Método de teste para salvar um novo equipamento
//         Equipamento equipamento = new Equipamento(1, "Aparelho A", "Modelo A", "Local A", "Especificações A", LocalDate.now()); // Cria um novo objeto Equipamento
        
//         doNothing().when(equipamentoDAOMock).salvar(equipamento); // Define o comportamento do mock para não fazer nada ao salvar
        
//         // Chama o método a ser testado
//         equipamentoController.salvarEquipamento(equipamento);
        
//         // Verifica se o método salvar foi chamado
//         verify(equipamentoDAOMock).salvar(equipamento); 
//     }

//     @Test // Anotação que marca o método como um teste
//     public void testAtualizarEquipamento() { // Método de teste para atualizar um equipamento existente
//         Equipamento equipamento = new Equipamento(1, "Aparelho A", "Modelo A", "Local A", "Especificações A", LocalDate.now()); // Cria um novo objeto Equipamento
        
//         doNothing().when(equipamentoDAOMock).atualizar(equipamento); // Define o comportamento do mock para não fazer nada ao atualizar
        
//         // Chama o método a ser testado
//         equipamentoController.atualizarEquipamento(equipamento);
        
//         // Verifica se o método atualizar foi chamado
//         verify(equipamentoDAOMock).atualizar(equipamento); 
//     }

//     @Test // Anotação que marca o método como um teste
//     public void testExcluirEquipamento() { // Método de teste para excluir um equipamento pelo ID
//         int id = 1; // ID do equipamento a ser excluído
        
//         doNothing().when(equipamentoDAOMock).deletar(id); // Define o comportamento do mock para não fazer nada ao deletar
        
//         // Chama o método a ser testado
//         equipamentoController.excluirEquipamento(id);
        
//         // Verifica se o método deletar foi chamado
//         verify(equipamentoDAOMock).deletar(id); 
//     }

//     @Test // Anotação que marca o método como um teste
//     public void testBuscarEquipamentoPorId() { // Método de teste para buscar um equipamento pelo ID
//         int id = 1; // ID do equipamento a ser buscado
//         Equipamento equipamento = new Equipamento(1, "Aparelho A", "Modelo A", "Local A", "Especificações A", LocalDate.now()); // Cria um novo objeto Equipamento

//         when(equipamentoDAOMock.buscarPorId(id)).thenReturn(equipamento); // Define o comportamento do mock para retornar o equipamento

//         // Chama o método a ser testado
//         Equipamento resultado = equipamentoController.buscarEquipamentoPorId(id);
        
//         // Verifica se o resultado é o esperado
//         assertEquals(equipamento, resultado); 
//         verify(equipamentoDAOMock).buscarPorId(id); // Verifica se o método buscarPorId foi chamado
//     }

//     @Test // Anotação que marca o método como um teste
//     public void testListarEquipamentos() { // Método de teste para listar todos os equipamentos
//         Equipamento equipamento1 = new Equipamento(1, "Aparelho A", "Modelo A", "Local A", "Especificações A", LocalDate.now()); // Cria um novo objeto Equipamento
//         Equipamento equipamento2 = new Equipamento(2, "Aparelho B", "Modelo B", "Local B", "Especificações B", LocalDate.now()); // Cria outro objeto Equipamento
//         List<Equipamento> equipamentos = Arrays.asList(equipamento1, equipamento2); // Cria uma lista de equipamentos

//         when(equipamentoDAOMock.listarTodos()).thenReturn(equipamentos); // Define o comportamento do mock para retornar a lista de equipamentos

//         // Chama o método a ser testado
//         List<Equipamento> resultado = equipamentoController.listarEquipamentos();
        
//         // Verifica se a lista retornada é a esperada
//         assertEquals(equipamentos, resultado); 
//         verify(equipamentoDAOMock).listarTodos(); // Verifica se o método listarTodos foi chamado
//     }

//     @Test // Anotação que marca o método como um teste
//     public void testBuscarEquipamentoPorLocal() { // Método de teste para buscar equipamentos por local
//         String local = "Local A"; // Local a ser buscado
//         Equipamento equipamento1 = new Equipamento(1, "Aparelho A", "Modelo A", local, "Especificações A", LocalDate.now()); // Cria um novo objeto Equipamento
//         Equipamento equipamento2 = new Equipamento(2, "Aparelho B", "Modelo B", local, "Especificações B", LocalDate.now()); // Cria outro objeto Equipamento
//         List<Equipamento> equipamentos = Arrays.asList(equipamento1, equipamento2); // Cria uma lista de equipamentos

//         when(equipamentoDAOMock.buscarPorLocal(local)).thenReturn(equipamentos); // Define o comportamento do mock para retornar a lista de equipamentos por local

//         // Chama o método a ser testado
//         List<Equipamento> resultado = equipamentoController.buscarEquipamentoPorLocal(local);
        
//         // Verifica se a lista retornada é a esperada
//         assertEquals(equipamentos, resultado); 
//         verify(equipamentoDAOMock).buscarPorLocal(local); // Verifica se o método buscarPorLocal foi chamado
//     }
// }
