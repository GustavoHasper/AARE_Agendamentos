package br.com.unipar.medicalcare.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import br.com.unipar.medicalcare.dao.PacienteDAO;
import br.com.unipar.medicalcare.entities.Paciente;

@Database(entities = {Paciente.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase{

    public abstract PacienteDAO pacienteDAO();

}
