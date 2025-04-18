package com.victorgomesdossantosTeste.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText editTextAmount;
    private EditText editTextCotacao;
    private TextView textViewResult;
    private Button buttonConvert;

    // Cotações padrão
    private static final double RATE_USD_DEFAULT = 5.70;
    private static final double RATE_EUR = 6.16;
    private static final double RATE_LBL = 7.38;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextAmount = findViewById(R.id.editTextAmount);
        editTextCotacao = findViewById(R.id.editTextCotacao);
        textViewResult = findViewById(R.id.textViewResult);
        buttonConvert = findViewById(R.id.buttonConvert);

        buttonConvert.setOnClickListener(v -> {
            String amountText = editTextAmount.getText().toString();
            String cotacaoText = editTextCotacao.getText().toString();

            if (amountText.isEmpty()) {
                Toast.makeText(MainActivity.this, "Informe um valor", Toast.LENGTH_SHORT).show();
                return;
            }

            double amountBRL = Double.parseDouble(amountText);
            double cotacaoUSD;

            if (cotacaoText.isEmpty()) {
                cotacaoUSD = RATE_USD_DEFAULT;
                Toast.makeText(MainActivity.this, "Cotação do dólar não informada, usando padrão: " + RATE_USD_DEFAULT, Toast.LENGTH_SHORT).show();
            } else {
                cotacaoUSD = Double.parseDouble(cotacaoText);
            }

            // Conversões
            double resultUSD = amountBRL / cotacaoUSD;
            double resultEUR = amountBRL / RATE_EUR;
            double resultLBL = amountBRL / RATE_LBL;

            // Exibindo o resultado
            String result = "Dólar (USD): R$ " + String.format("%.2f", resultUSD) + "\n" +
                    "Euro (EUR): R$ " + String.format("%.2f", resultEUR) + "\n" +
                    "Libra (LBL): R$ " + String.format("%.2f", resultLBL);
            textViewResult.setText(result);
        });
    }
}
