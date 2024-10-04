# Sistema de Manutenção Preventiva e Corretiva

## Diagrama de Classes

```mermaid
classDiagram
    class Tecnico {
        +int id
        +String nome
        +String especialidade
        +salvarTecnico()
    }

    class Equipamento {
        +int id
        +String aparelho
        +String modelo
        +String local
        +salvarEquipamento()
    }

    class Manutencao {
        +int id
        +int idTecnico
        +int idEquipamento
        +String data
        +String descricao
        +String status
        +salvarManutencao()
        +marcarComoConcluida()
        +marcarComoPendente()
    }

    Tecnico "1" -- "*" Manutencao : realiza
    Equipamento "1" -- "*" Manutencao : é mantido

## Diagrama de Fluxos

flowchart TD
    A[Início] --> B[Tela Inicial]
    B --> C{Selecionar Módulo}
    C -->|Cadastro de Equipamento| D[TelaCadastroEquipamento]
    C -->|Cadastro de Técnico| E[TelaCadastroTecnico]
    C -->|Cadastro de Manutenção| F[TelaCadastroManutencao]
    C -->|Lista de Manutenções| G[TelaListaManutencao]

    D --> H[Preencher Dados do Equipamento]
    H --> I[Salvar Equipamento]
    I --> B

    E --> J[Preencher Dados do Técnico]
    J --> K[Salvar Técnico]
    K --> B

    F --> L[Preencher Dados da Manutenção]
    L --> M[Salvar Manutenção]
    M --> B

    G --> N[Listar Manutenções]
    N --> O{Selecionar Manutenção}
    O -->|Concluída| P[Marcar como Concluída]
    O -->|Pendente| Q[Marcar como Pendente]
    P --> G
    Q --> G

## Diagrama de Uso

flowchart TD
    A[Início] --> B[Tela Inicial]
    
    B --> C{Selecionar Módulo}
    
    C -->|Cadastro de Equipamento| D[Tela Cadastro Equipamento]
    D --> E[Preencher Dados do Equipamento]
    E --> F[Salvar Equipamento]
    F --> G[Confirmação de Cadastro]
    G --> B
    
    C -->|Cadastro de Técnico| H[Tela Cadastro Técnico]
    H --> I[Preencher Dados do Técnico]
    I --> J[Salvar Técnico]
    J --> K[Confirmação de Cadastro]
    K --> B
    
    C -->|Cadastro de Manutenção| L[Tela Cadastro Manutenção]
    L --> M[Preencher Dados da Manutenção]
    M --> N[Salvar Manutenção]
    N --> O[Confirmação de Cadastro]
    O --> B
    
    C -->|Lista de Manutenções| P[Tela Lista Manutenções]
    P --> Q[Listar Manutenções]
    Q --> R{Selecionar Manutenção}
    
    R -->|Marcar como Concluída| S[Marcar como Concluída]
    S --> T[Atualizar Status]
    T --> U[Confirmação de Atualização]
    U --> P
    
    R -->|Marcar como Pendente| V[Marcar como Pendente]
    V --> W[Atualizar Status]
    W --> X[Confirmação de Atualização]
    X --> P
    
    R -->|Voltar| P
