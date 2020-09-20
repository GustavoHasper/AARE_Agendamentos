package br.com.unipar.agendamento;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;


import br.com.unipar.agendamento.adapter.PacienteAdapter;
import br.com.unipar.agendamento.dao.PacienteDAO;
import br.com.unipar.agendamento.database.AppDatabase;
import br.com.unipar.agendamento.database.RoomDatabaseOpenHelper;
import br.com.unipar.agendamento.entities.Paciente;

public class ListaFiltraPaciente extends AppCompatActivity {

    private ListView pacientes;
    private List<Paciente> listaPacientes = new ArrayList<Paciente>();
    private EditText nome, hora, medico;
    private String nomeTemp, horaTemp, medicoTemp, id;
    private CheckBox checkBox1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secundary);

        this.pacientes = findViewById(R.id.lstConsulta);

        listPacienteThrd("", "", "", "");
    }


    public void listar(View view){
        nomeTemp = "";
        horaTemp = "";
        medicoTemp = "";

        nome = findViewById(R.id.edPaciente);
        hora = findViewById(R.id.edAgendamento);
        medico = findViewById(R.id.edDoutor);

        if(!nome.getText().toString().isEmpty()) {
           nomeTemp = nome.getText().toString();
        }
        if(!hora.getText().toString().isEmpty()){
            horaTemp = hora.getText().toString();
        }
        if(!medico.getText().toString().isEmpty()){
            medicoTemp = medico.getText().toString();
        }

        listPacienteThrd(nomeTemp, horaTemp, medicoTemp, "");
    }

    public  void finalizar(View view){
        id = "";
        checkBox1 = findViewById(R.id.checkBox);

        if(checkBox1.isChecked()) {
            id = String.valueOf(checkBox1.getTag());

            listPacienteThrd("", "", "", id);
            limpaForm();
        }else{
            mensagem("Pra completar o atendimento selecione um registro:");
        }
    }

    public  void voltar(View view){
        Intent intent = new Intent(this, MainActivity.class);
        Bundle b = new Bundle();
        intent.putExtras(b);
        startActivity(intent);
    }

    
    public void  listPacienteThrd(String nome, String hora, String medico, String id) {
        AppDatabase appDatabase = RoomDatabaseOpenHelper.getDatabase(new WeakReference<Context>(this));
        new Thread(new Runnable() {
            @Override
            public void run() {
                PacienteDAO pacienteDao = appDatabase.pacienteDAO();

                if(!id.equals("")){
                    pacienteDao.Deleta(id);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            mensagem("Atendimento acabou!");
                        }
                    });
                }
                if(nome.equals("") && hora.equals("") && medico.equals("")){
                    listaPacientes = pacienteDao.listaPacientes();
                }
                else{
                    if(!nome.equals("")){
                        listaPacientes = pacienteDao.listaPacienteNome(nome);
                    }
                    else if(!hora.equals("")){
                        listaPacientes = pacienteDao.listaPacientehora(hora);
                    }
                    else if(!medico.equals("")){
                        listaPacientes = pacienteDao.listaPacienteMedico(medico);
                    }
                }

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        listaPaciente(listaPacientes);
                    }
                });
            }
        }).start();
    }


    public void listaPaciente(List<Paciente> listaTodos){

        final WeakReference<Context> weakReference = new WeakReference(this);
        PacienteAdapter pacienteAdapter = new PacienteAdapter(listaTodos, weakReference);
        pacientes.setAdapter(pacienteAdapter);
        this.listaPacientes = listaTodos;
    }

    public void mensagem(String texto){
        Toast.makeText(this, texto, Toast.LENGTH_LONG).show();
    }

    public void limpaForm(){
        nome.setText("");
        hora.setText("");
        medico.setText("");
    }
}
