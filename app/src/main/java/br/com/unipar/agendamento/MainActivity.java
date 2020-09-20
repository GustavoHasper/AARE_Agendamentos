package br.com.unipar.agendamento;

import androidx.appcompat.app.AppCompatActivity;
import br.com.unipar.agendamento.dao.PacienteDAO;
import br.com.unipar.agendamento.database.AppDatabase;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.github.rtoshiro.util.format.SimpleMaskFormatter;
import com.github.rtoshiro.util.format.text.MaskTextWatcher;

import java.lang.ref.WeakReference;


import br.com.unipar.agendamento.database.RoomDatabaseOpenHelper;
import br.com.unipar.agendamento.entities.Paciente;

public class MainActivity extends AppCompatActivity {

    private String textoPadrao = "deve ser informado";
    private EditText nome, hora, medico, celular;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        celular = findViewById(R.id.edCelular);
        celular.addTextChangedListener(new MaskTextWatcher(celular, new SimpleMaskFormatter("(NN)NNNNN-NNNN")));
        hora = findViewById(R.id.edhora);
        hora.addTextChangedListener(new MaskTextWatcher(hora, new SimpleMaskFormatter("NN/NN/NNNN--NN:NN")));
    }


    public void  marcar(View view){

        nome = findViewById(R.id.edNome);
        medico = findViewById(R.id.edMedico);

        if(nome.getText().toString().equals("")) mensagem("Nome "+ textoPadrao);
        else if(hora.getText().toString().equals("")) mensagem("hora "+ textoPadrao);
        else if(medico.getText().toString().equals("")) mensagem("Medico "+ textoPadrao);
        else if(celular.getText().toString().equals("")) mensagem("Celular "+ textoPadrao);
        else{
            salvaPaciente(nome.getText().toString(), hora.getText().toString(),
                          medico.getText().toString(), celular.getText().toString());
        }
    }


    public void  consultar(View view){
        segundaTela(view);
    }


    public void salvaPaciente(String nome, String hora, String medico, String celular){
        Paciente paciente = new Paciente();
        paciente.setNome(nome);
        paciente.setHoraAgendamento(hora);
        paciente.setNomeMedico(medico);
        paciente.setCelular(celular);

        salvaPacienteThrd(paciente);
    }


    public void salvaPacienteThrd(Paciente paciente) {
        AppDatabase appDatabase = RoomDatabaseOpenHelper.getDatabase(new WeakReference<Context>(this));
        new Thread(new Runnable() {
            public void run() {
                PacienteDAO pacienteDao = appDatabase.pacienteDAO();
                pacienteDao.insertAll(paciente);
            }
        }).start();
        mensagem("Cadastro Finalizado!");
        limparFormulario();
    }


    public void segundaTela(View view){
        Intent intent = new Intent(this, ListaFiltraPaciente.class);
        Bundle b = new Bundle();
        intent.putExtras(b);
        startActivity(intent);
    }


    public void limparFormulario(){
        nome.setText("");
        hora.setText("");
        medico.setText("");
        celular.setText("");
    }

    public void mensagem(String texto){
        Toast.makeText(this, texto, Toast.LENGTH_LONG).show();
    }

}
