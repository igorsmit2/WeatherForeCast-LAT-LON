package br.usjt;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class LocalizacaoDAO {

    private Context context;

    public LocalizacaoDAO(Context context) {
        this.context = context;
    }

    public List<Localizacao> recuperaLocalizacoes() {
        SQLHelper helper = new SQLHelper(context);
        SQLiteDatabase database = helper.getReadableDatabase();
        String query = String.format(Locale.getDefault(), "SELECT * FROM %s ORDER BY %s DESC LIMIT 50", Contract.TABLE_NAME, Contract.COLUMN_ID);
        Cursor cursor = database.rawQuery(query, null);
        ArrayList<Localizacao> localizacoes = new ArrayList<>();

        try {
            while (cursor.moveToNext()) {
                double latitude = cursor.getDouble(cursor.getColumnIndex(Contract.COLUMN_LATITUDE));
                double longitude = cursor.getDouble(cursor.getColumnIndex(Contract.COLUMN_LONGITUDE));
                long data = cursor.getLong(cursor.getColumnIndex(Contract.COLUMN_DIA_SEMANA));
                double minTemp = cursor.getDouble(cursor.getColumnIndex(Contract.COLUMN_MIN_TEMP));
                double maxTemp = cursor.getDouble(cursor.getColumnIndex(Contract.COLUMN_MAX_TEMP));
                double umidade = cursor.getDouble(cursor.getColumnIndex(Contract.COLUMN_UMIDADE));
                String descricao = cursor.getString(cursor.getColumnIndex(Contract.COLUMN_DESCRICAO));

                localizacoes.add(0, new Localizacao(latitude, longitude, new Clima(data, minTemp, maxTemp, umidade, descricao)));
            }
        } finally {
            cursor.close();
            database.close();
            helper.close();
        }

        return localizacoes;
    }

    public void insertLocation(Localizacao localizacao) {
        SQLHelper helper = new SQLHelper(context);
        SQLiteDatabase database = helper.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(Contract.COLUMN_LATITUDE, localizacao.latitude);
        contentValues.put(Contract.COLUMN_LONGITUDE, localizacao.longitude);
        contentValues.put(Contract.COLUMN_DIA_SEMANA, localizacao.clima.diaSemana);
        contentValues.put(Contract.COLUMN_MIN_TEMP, localizacao.clima.minTemp);
        contentValues.put(Contract.COLUMN_MAX_TEMP, localizacao.clima.maxTemp);
        contentValues.put(Contract.COLUMN_UMIDADE, localizacao.clima.umidade);
        contentValues.put(Contract.COLUMN_DESCRICAO, localizacao.clima.descricao);

        database.insert(Contract.TABLE_NAME, null, contentValues);
        database.close();
    }
}
