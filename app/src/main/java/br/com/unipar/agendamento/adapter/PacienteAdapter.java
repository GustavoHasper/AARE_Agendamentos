package br.com.unipar.agendamento.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;


import java.lang.ref.WeakReference;
import java.util.List;

import br.com.unipar.agendamento.R;
import br.com.unipar.agendamento.entities.Paciente;

public class PacienteAdapter extends BaseAdapter {

    private List<Paciente> pacientes;
    private WeakReference<Context> weakReference;
    private LayoutInflater inflater;
    private CheckBox checkBox1, chk;


    public PacienteAdapter(List<Paciente> pacientes, WeakReference<Context> weakReference) {
        this.pacientes = pacientes;
        this.weakReference = weakReference;
        this.inflater = LayoutInflater.from(weakReference.get());
    }

    public void adicionaPaciente(Paciente pacientes){
        this.pacientes.add(pacientes);
    }

    @Override
    public int getCount() {
        return pacientes.size();
    }

    @Override
    public Object getItem(int i) {
        return pacientes.get(i);
    }

    @Override
    public long getItemId(int i) {
        return pacientes.get(i).getId();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        ViewHolder viewHolder;
        Paciente paciente = (Paciente) getItem(i);

        if (view == null) {
            view = inflater.inflate(R.layout.lista, null);

            viewHolder = new ViewHolder();

            viewHolder.id = view.findViewById(R.id.checkBox);
            viewHolder.Nome = view.findViewById(R.id.tvNome);
            viewHolder.Agendamento = view.findViewById(R.id.tvAgendamento);
            viewHolder.Medico = view.findViewById(R.id.tvMedico);
            viewHolder.Celular = view.findViewById(R.id.tvCelular);

            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }

        viewHolder.Nome.setText(paciente.getNome());
        viewHolder.Agendamento.setText(paciente.getHoraAgendamento());
        viewHolder.Medico.setText(paciente.getNomeMedico());
        viewHolder.Celular.setText(paciente.getCelular());
        viewHolder.id.setTag(String.valueOf(paciente.getId()));

        return view;
    }

    static class ViewHolder {
        CheckBox id;
        TextView Nome;
        TextView Agendamento;
        TextView Medico;
        TextView Celular;
    }

}
