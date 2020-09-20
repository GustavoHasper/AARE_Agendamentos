package br.com.unipar.medicalcare.entities;

import android.icu.util.LocaleData;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "pacientes")
public class Paciente {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo
    private String nome;

    @ColumnInfo
    private String horaAgendamento;

    @ColumnInfo
    private String nomeMedico;

    @ColumnInfo
    private String celular;


    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getHoraAgendamento() {
        return horaAgendamento;
    }
    public void setHoraAgendamento(String horaAgendamento) {
        this.horaAgendamento = horaAgendamento;
    }
    public String getNomeMedico() {
        return nomeMedico;
    }
    public void setNomeMedico(String nomeMedico) {
        this.nomeMedico = nomeMedico;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

}
