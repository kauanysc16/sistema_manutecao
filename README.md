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

@startuml
actor Usuario
actor Administrador

Usuario --> (Cadastrar Tecnico)
Usuario --> (Cadastrar Equipamento)
Usuario --> (Agendar Manutencao)
Usuario --> (Registrar Falha)
Usuario --> (Gerar Relatorio)
Usuario --> (Verificar Disponibilidade)

Administrador --> (Cadastrar Tecnico)
Administrador --> (Cadastrar Equipamento)
Administrador --> (Agendar Manutencao)
Administrador --> (Registrar Falha)
Administrador --> (Gerar Relatorio)
Administrador --> (Verificar Disponibilidade)
@enduml


#### Diagrama de Fluxos

@startuml
start
:Inicio;
if (Cadastro de Tecnico?) then (sim)
  :Preencher dados do Tecnico;
  :Salvar Tecnico;
else (não)
  if (Cadastro de Equipamento?) then (sim)
    :Preencher dados do Equipamento;
    :Salvar Equipamento;
  else (não)
    if (Agendamento de Manutencao?) then (sim)
      :Selecionar Equipamento e Tecnico;
      :Preencher dados da Manutencao;
      :Salvar Manutencao;
    else (não)
      if (Registro de Falha?) then (sim)
        :Selecionar Manutencao;
        :Preencher dados da Falha;
        :Salvar Falha;
      else (não)
        if (Geracao de Relatorio?) then (sim)
          :Selecionar tipo de Relatorio;
          :Gerar Relatorio;
        else (não)
          if (Verificacao de Disponibilidade?) then (sim)
            :Verificar disponibilidade dos Tecnicos;
          endif
        endif
      endif
    endif
  endif
endif
:Encerrar;
stop
@enduml
