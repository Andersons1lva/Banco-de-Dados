package com.anderson.bancodedados

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Sempre usar o try/catch para criação do Banco de Dados pelo fato dos dados serem sensiveis e poder tratar se houver algum erro.
        try{
            //Criar Banco de Dados
            val bancoDados = openOrCreateDatabase("DB_FORNECEDORES", MODE_PRIVATE, null)

            //Criar uma tabela
            bancoDados.execSQL("CREATE TABLE IF NOT EXISTS fornecedores(nome VARCHAR, telefone INT(4))")

            //inserir dados em uma tabela
            //bancoDados.execSQL("INSERT INTO fornecedores (nome, telefone) VALUES ('Academia Gourmet', 102030)")

            val consulta = "SELECT nome, telefone FROM fornecedores"

            //Criar cursor para acessar o banco de dados
            val cursor = bancoDados.rawQuery(consulta,null)

            //recuperar os indices da nossa tabela
            val indiceNome = cursor.getColumnIndex("nome")
            val indiceTelefone = cursor.getColumnIndex("telefone")
            cursor.moveToFirst() // vai percorre e volta para o inicio da tabela.

            while (cursor != null) {
                val nome = cursor.getString(indiceNome)
                val telefone = cursor.getString(indiceTelefone)
                Log.i("RESULTADO","/nome: $nome/ telefone: $telefone")
                cursor.moveToNext()
            }



        }catch(e: Exception){
            e.printStackTrace()
        }
    }
}