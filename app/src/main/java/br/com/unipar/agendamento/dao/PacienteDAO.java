package br.com.unipar.medicalcare.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import br.com.unipar.medicalcare.entities.Paciente;

@Dao
public interface PacienteDAO {

    @Query("SELECT * FROM pacientes")
    List<Paciente> listaPacientes();

    @Query("SELECT * FROM pacientes where nome = :nome")
    List<Paciente> listaPacienteNome(String nome);

    @Query("SELECT * FROM pacientes where horaAgendamento = :horario")
    List<Paciente> listaPacienteHorario(String horario);

    @Query("SELECT * FROM pacientes where nomeMedico = :medico")
    List<Paciente> listaPacienteMedico(String medico);

    @Query("DELETE FROM pacientes where id = :id")
    void Deleta(String id);


    @Insert
    void insertAll(Paciente paciente);
}
