package com.example.behappy.behappyapp.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.behappy.behappyapp.NewsNavigationActivity;
import com.example.behappy.behappyapp.R;
import com.example.behappy.behappyapp.adapters.NewsListAdapter;
import com.example.behappy.behappyapp.classes.News;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Matheus on 28/06/2016.
 */
public class NewsFeedFragment extends Fragment {

    private List<News> newsFeed = new ArrayList<News>();
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //View root = inflater.inflate(R.layout.fragment_newsfeed, container, false);
        View root = inflater.inflate(R.layout.fragment_mainboard, container, false);
        populateNewsFeed();
        populateNewsListView(root);
        registerClickCallback(root);
        return root;
    }

    private void registerClickCallback(View root) {
        ListView list = (ListView) root.findViewById(R.id.board_newsListView);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                News clickedNews = newsFeed.get(position);

                Intent intent = new Intent(getActivity(), NewsNavigationActivity.class);
                intent.putExtra("news", clickedNews);

                startActivity(intent);
            }
        });
    }

    private void populateNewsListView(View root) {
        ArrayAdapter<News> adapter = new NewsListAdapter(getActivity(), R.layout.listview_news, newsFeed);
        ListView list = (ListView) root.findViewById(R.id.board_newsListView);
        list.setAdapter(adapter);
    }

    private void populateNewsFeed() {
        /*newsFeed.add(new News("Coca-Cola Zero é proibida nos EUA.", "E no Brasil, sete refrigerantes têm substância cancerígena", "10/02/2015"
        , "labclinicas.com.br", "Julliana Botelho Decourt", R.mipmap.cocacolaimg,
                "Coca-cola Zero. Sukita Zero. Fanta Light. Dolly Guaraná. Dolly Guaraná Diet. Fanta Laranja. Sprite Zero. Sukita. Oito bebidas e duas substâncias altamente nocivas ao ser humano. Na Coca-cola Zero, está o ciclamato de sódio, um agente químico que reconhecidamente faz mal à saúde. Nos outros sete refrigerantes, está o benzeno, uma substância potencialmente cancerígena.Essa é a mais recente descoberta que vem sendo publicada na mídia e que só agora chega aos ouvidos das maiores vítimas do refrigerante: os consumidores. A pergunta que vem logo à mente é: “por que só agora isso está sendo divulgado?”. E, pior: “se estes refrigerantes fazem tão mal à saúde, por que sua venda é permitida?”.\n" +
                        "\n" +
                        "Nos Estados Unidos da América, a Coca-cola Zero já é proibida pelo F.D.A. (Federal Drugs Administration), mas sua venda continua em alta nos países em desenvolvimento ou não desenvolvidos, como os da Europa Oriental e América Latina. O motivo é o baixo custo do ciclamato de sódio (10 dólares por quilo) quando comparado ao Aspartame (152 dólares/Kg), substância presente na Coca-cola Light. O que isso quer dizer? Simplesmente que mesmo contendo substância danosa à saúde, a Coca Zero resulta num baixo custo para a companhia, tendo por isso uma massificação da propaganda para gerar mais vendas.\n" +
                        "\n" +
                        "Não basta o cigarro?\n" +
                        "\n" +
                        "Uma pesquisa realizada pela Pro Teste – Associação Brasileira de Defesa do Consumidor – verificou a presença do benzeno em índices alarmantes na Sukita Zero (20 microgramas por litro) e na Fanta Light (7,5 microgramas). Já nos refrigerantes Dolly Guaraná, Dolly Guaraná Diet, Fanta Laranja, Sprite Zero e Sukita, o índice de benzeno estava abaixo do limite de 5 microgramas por litro.\n" +
                        "\n" +
                        "Só para se ter uma idéia, o benzeno está presente no ambiente através da fumaça do cigarro e da queima de combustível. Agora, imagine isso no seu organismo ao ingerir um dos refrigerantes citados. Utilizado como matéria-prima de produtos como detergente, borracha sintética e náilon, o benzeno está relacionado a leucemias e ao linfoma. Contudo, apesar de seus malefícios, o consumo da substância não significa necessariamente que a pessoa terá câncer, pois cada organismo tem seu nível de tolerância e vulnerabilidade.\n" +
                        "Corantes e adoçantes\n" +
                        "\n" +
                        "Na mesma pesquisa da Pro Teste, constatou-se que as crianças correm um grande risco, pois foram encontrados adoçantes na versão tradicional do Grapette, não informados no rótulo. Nos refrigerantes Fanta Laranja, Fanta Laranja Light, Grapette, Grapette Diet, Sukita e Sukita Zero, foram identificados os corantes amarelo crepúsculo, que favorece a hiperatividade infantil e já foi proibido na Europa, e o amarelo tartrazina, com alto potencial alérgico.\n" +
                        "\n" +
                        "Enquanto a pesquisa acusa uma urgente substituição dos corantes por ácido benzóico, por exemplo, a Coca-cola, que produz a Fanta, defende-se dizendo que cumpre a lei e informa a presença dos corantes nos rótulos das bebidas. A AmBev, que fabrica a Sukita, informou que trabalha “sob os mais rígidos padrões de qualidade e em total atendimento à legislação brasileira”.\n" +
                        "\n" +
                        "Por fim, a Refrigerantes Pakera, fabricante do Grapette, diz que a bebida pode ter sido contaminada por adoçantes porque as duas versões são feitas na mesma máquina e algum resíduo pode ter ficado nos tanques.\n" +
                        "\n" +
                        "Quando será o fim dessa novela e da venda dos refrigerantes que contém substâncias nocivas à saúde, ninguém sabe. Mas enquanto os fabricantes deixam a ética e o respeito ao cidadão de lado em busca do lucro exacerbado, você tem a liberdade de decidir entre tomar esse veneno ou preservar a qualidade do seu organismo. Agora, é com você!",
                50));
        newsFeed.add (new News("Entenda porque refresco “em pó” prejudica a saúde", "Com o passar do tempo e com o uso constante de tais produtos, pode revelar consequências danosas ao organismo","30/03/2013", "dietaesaude.net" , "Leonardo Freitas", R.mipmap.sucoimg, "body...", 20 ));
        newsFeed.add (new News("7 Alimentos cancerígenos que você deveria parar de comer agora mesmo", "Pipoca de micro-ondas é um dos principais.", "20/04/2014", "melhorcomsaude.com", "", R.mipmap.image_example, "body...", 15));
        */
    }


}


