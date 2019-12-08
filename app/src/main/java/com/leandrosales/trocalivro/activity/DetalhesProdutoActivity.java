package com.leandrosales.trocalivro.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.leandrosales.trocalivro.R;
import com.leandrosales.trocalivro.model.Anuncio;
import com.squareup.picasso.Picasso;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;

public class DetalhesProdutoActivity extends AppCompatActivity {

    private CarouselView carouselView;
    private TextView titulo;
    private TextView edicao;
    private TextView estado;
    private TextView autor;
    private Anuncio anuncioSelecionado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes_produto);

        //Configurar toolbar
        getSupportActionBar().setTitle("Detalhe produto");

        //Incializar componentes de interface
        inicializarComponentes();

        //Recupera anúncio para exibicao
        anuncioSelecionado = (Anuncio) getIntent().getSerializableExtra("anuncioSelecionado");

        if( anuncioSelecionado != null ){

            titulo.setText( anuncioSelecionado.getTitulo() );
            edicao.setText( anuncioSelecionado.getEdicao() );
            estado.setText( anuncioSelecionado.getEstado() );
            autor.setText( anuncioSelecionado.getAutor());

            ImageListener imageListener = new ImageListener() {
                @Override
                public void setImageForPosition(int position, ImageView imageView) {
                    String urlString = anuncioSelecionado.getFotos().get( position );
                    Picasso.get().load(urlString).into(imageView);
                }
            };

            carouselView.setPageCount( anuncioSelecionado.getFotos().size() );
            carouselView.setImageListener( imageListener );

        }

    }

    public void visualizarTelefone(View view){
        Intent i = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", anuncioSelecionado.getTelefone(), null ));
        startActivity( i );
    }

    private void inicializarComponentes(){
        carouselView = findViewById(R.id.carouselView);
        titulo = findViewById(R.id.textTituloDetalhe);
        edicao = findViewById(R.id.textEdicao);
        estado = findViewById(R.id.textEstadoDetalhe);
        autor = findViewById(R.id.textAutor);
    }

}
