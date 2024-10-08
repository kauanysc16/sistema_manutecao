# Sistema de Manutenção Preventiva e Corretiva

## Descrição do Projeto

Este projeto é um sistema de gerenciamento de manutenção preventiva e corretiva para máquinas e equipamentos industriais. O sistema permite controlar manutenções preventivas, registrar falhas, gerenciar a alocação de técnicos e gerar relatórios detalhados sobre o histórico de manutenção e desempenho dos equipamentos.

## Tecnologias Utilizadas

- Java
- PostgreSQL, MongoDB, ou Json-Server para persistência de dados
- Swing ou JavaFX para interfaces gráficas
- JUnit para testes unitários

## Funcionalidades

- Cadastro de técnicos, equipamentos e manutenções
- Agendamento de manutenções preventivas e corretivas
- Registro de falhas e peças substituídas
- Gerenciamento da alocação de técnicos
- Geração de relatórios de manutenção
- Verificação de disponibilidade de técnicos

## Diagramas

#### Diagrama de Classes

@startuml
class Tecnico {
  -int id
  -String nome
  -String especialidade
  -boolean disponivel
  +getId()
  +setId(int)
  +getNome()
  +setNome(String)
  +getEspecialidade()
  +setEspecialidade(String)
  +isDisponivel()
  +setDisponivel(boolean)
}

class Equipamento {
  -int id
  -String aparelho
  -String modelo
  -String local
  +getId()
  +setId(int)
  +getAparelho()
  +setAparelho(String)
  +getModelo()
  +setModelo(String)
  +getLocal()
  +setLocal(String)
}

class Manutencao {
  -int id
  -int idEquipamento
  -int idTecnico
  -String tipo
  -String descricao
  -LocalDate data
  -String status
  -String pecasSubstituidas
  -int tempoInatividade
  +getId()
  +setId(int)
  +getIdEquipamento()
  +setIdEquipamento(int)
  +getIdTecnico()
  +setIdTecnico(int)
  +getTipo()
  +setTipo(String)
  +getDescricao()
  +setDescricao(String)
  +getData()
  +setData(LocalDate)
  +getStatus()
  +setStatus(String)
  +getPecasSubstituidas()
  +setPecasSubstituidas(String)
  +getTempoInatividade()
  +setTempoInatividade(int)
}

Tecnico "1" *-- "0..*" Manutencao
Equipamento "1" *-- "0..*" Manutencao
@enduml


#### Diagrama de Uso

%%{ init : { "theme" : "default" } }%%
graph TD;
    Usuario --> Cadastrar_Tecnico
    Usuario --> Cadastrar_Equipamento
    Usuario --> Agendar_Manutencao
    Usuario --> Registrar_Falha
    Usuario --> Gerar_Relatorio
    Usuario --> Verificar_Disponibilidade

    Administrador --> Cadastrar_Tecnico
    Administrador --> Cadastrar_Equipamento
    Administrador --> Agendar_Manutencao
    Administrador --> Registrar_Falha
    Administrador --> Gerar_Relatorio
    Administrador --> Verificar_Disponibilidade



#### Diagrama de Fluxos

%%{ init : { "theme" : "default" } }%%
graph TD;
    A[Inicio] --> B{Cadastro de Tecnico?}
    B -- Sim --> C[Preencher dados do Tecnico]
    C --> D[Salvar Tecnico]
    B -- Não --> E{Cadastro de Equipamento?}
    E -- Sim --> F[Preencher dados do Equipamento]
    F --> G[Salvar Equipamento]
    E -- Não --> H{Agendamento de Manutencao?}
    H -- Sim --> I[Selecionar Equipamento e Tecnico]
    I --> J[Preencher dados da Manutencao]
    J --> K[Salvar Manutencao]
    H -- Não --> L{Registro de Falha?}
    L -- Sim --> M[Selecionar Manutencao]
    M --> N[Preencher dados da Falha]
    N --> O[Salvar Falha]
    L -- Não --> P{Geracao de Relatorio?}
    P -- Sim --> Q[Selecionar tipo de Relatorio]
    Q --> R[Gerar Relatorio]
    P -- Não --> S{Verificacao de Disponibilidade?}
    S -- Sim --> T[Verificar disponibilidade dos Tecnicos]
    A --> U[Encerrar]
