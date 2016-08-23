package com.example.behappy.behappyapp;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.behappy.behappyapp.adapters.CommentListAdapter;
import com.example.behappy.behappyapp.adapters.GroupListAdapter;
import com.example.behappy.behappyapp.adapters.ProductIngredientListAdapter;
import com.example.behappy.behappyapp.classes.Comment;
import com.example.behappy.behappyapp.classes.Ingredient;
import com.example.behappy.behappyapp.classes.Manufacturer;
import com.example.behappy.behappyapp.classes.Product;
import com.ramotion.foldingcell.FoldingCell;

import com.squareup.picasso.Picasso;
import com.yalantis.contextmenu.lib.ContextMenuDialogFragment;
import com.yalantis.contextmenu.lib.MenuObject;
import com.yalantis.contextmenu.lib.MenuParams;
import com.yalantis.contextmenu.lib.interfaces.OnMenuItemClickListener;
import com.yalantis.contextmenu.lib.interfaces.OnMenuItemLongClickListener;

import java.util.ArrayList;
import java.util.List;

import it.gmariotti.cardslib.library.internal.Card;

public class ProductActivity extends AppCompatActivity implements OnMenuItemClickListener, OnMenuItemLongClickListener, ExpandableListView.OnChildClickListener {

    ArrayList<Card> cards = new ArrayList<Card>();
    ArrayList<Ingredient> ingredients = new ArrayList<Ingredient>();
    Product product;
    //private BoomMenuButton boomMenuButton;
    private boolean init = false;
    private FragmentManager fragmentManager;
    private ContextMenuDialogFragment mMenuDialogFragment;
    private Ingredient selectedIngredient;

    private ArrayList<String> groupItem = new ArrayList<>();
    private ArrayList<Object> childItem = new ArrayList<Object>();

    private List<Comment> commentList = new ArrayList<Comment>();

    private ExpandableListView expandableList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Produto");
        setContentView(R.layout.activity_product);

        // prepare elements to display
        fragmentManager = getSupportFragmentManager();
        initMenuFragment();
        createProduct();
        populateIngredientList();

        final FoldingCell cell = (FoldingCell) findViewById(R.id.product_info_cell);
        final FoldingCell ingredientsCell = (FoldingCell) findViewById(R.id.product_ingredients_cell);
        final FoldingCell nutrientCell = (FoldingCell) findViewById(R.id.product_nutrients_cell);
        final FoldingCell groupsCell = (FoldingCell) findViewById(R.id.product_groups_cell);
        final FoldingCell commentsCell = (FoldingCell) findViewById(R.id.product_comments_cell);

        expandableList = (ExpandableListView) findViewById(R.id.groups_expandableList);
        expandableList.setDividerHeight(2);
        expandableList.setGroupIndicator(null);
        expandableList.setClickable(true);

        setGroupData();
        setChildGroupData();
        setGroupAdapter();

        populateCommentList();
        populateCommentView();
        populateRecommendations();



        ViewHolder viewHolder;
        viewHolder = new ViewHolder();
        // binding view parts to view holder
        viewHolder.name = (TextView) cell.findViewById(R.id.product_title_name);
        viewHolder.innerName = (TextView) cell.findViewById(R.id.product_title_content_name);
        viewHolder.manufacturer = (TextView) cell.findViewById(R.id.product_title_manufacture_value);
        viewHolder.category = (TextView) cell.findViewById(R.id.product_title_category_value);
        viewHolder.imgAvatar = (ImageView) cell.findViewById(R.id.product_title_avatarImg);
        viewHolder.countryOrigin = (TextView) cell.findViewById(R.id.product_title_origin);
        //viewHolder.portionType = (TextView) cell.findViewById(R.id.product_title_portion_type);
        //viewHolder.portionValue = (TextView) cell.findViewById(R.id.product_title_portion_count);
        viewHolder.curiosity = (TextView) cell.findViewById(R.id.product_title_content_curiosity);
        viewHolder.ingredientQuantity = (TextView) ingredientsCell.findViewById(R.id.product_ingredients_qtd);
        cell.setTag(viewHolder);
        ingredientsCell.setTag(viewHolder);
        ingredientsCell.initialize(1000, R.color.colorPrimaryDark, 5);

        cell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cell.toggle(false);
            }
        });
        ingredientsCell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { ingredientsCell.toggle(false); } });
        nutrientCell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nutrientCell.toggle(false);
            }
        });
        groupsCell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                groupsCell.toggle(false);
            }
        });
        commentsCell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                commentsCell.toggle(false);
            }
        });

        // bind data from selected element to view through view holder
        viewHolder.name.setText(product.getName());
        viewHolder.innerName.setText(product.getName());
        viewHolder.manufacturer.setText(product.getManufacturer().getName());
        viewHolder.category.setText(product.getCategory());
        //viewHolder.imgAvatar.setImageResource(product.getImgResourceID());
        Picasso.with(this).load(product.getImgResourceID()).into(viewHolder.imgAvatar);
        viewHolder.countryOrigin.setText(product.getCountryOrigin());
        //viewHolder.portionType.setText(product.getPortionType());
        //viewHolder.portionValue.setText(String.valueOf(product.getPortionValue()));
        viewHolder.curiosity.setText(product.getCuriosity());
        viewHolder.ingredientQuantity.setText("" + ingredients.size());

        /*boomMenuButton = (BoomMenuButton) findViewById(R.id.boom);
        boomMenuButton.setOnSubButtonClickListener(new BoomMenuButton.OnSubButtonClickListener() {
            @Override
            public void onClick(int buttonIndex) {
                // return the index of the sub button clicked
            }
        });*/


    }

    // View lookup cache
    private static class ViewHolder {
        TextView name;
        TextView innerName;
        TextView manufacturer;
        TextView category;
        ImageView imgAvatar;
        TextView countryOrigin;
        TextView portionType;
        TextView portionValue;
        TextView curiosity;
        TextView ingredientQuantity;
    }


    private void createProduct(){
        Manufacturer manufacturer = new Manufacturer(0, "AMBEV", "http://www.site.com", "08000 777 111");
        product = new Product(0, "Coca-Cola Zero (Lata)", "http://cdn.delivoro.com.br/f/public/arquivos/117625f62/img/prato/11361.jpg", "Brasil", manufacturer, 0, "Curiosidade");
        product.setCuriosity("Coca-cola Zero. Sukita Zero. Fanta Light. Dolly Guaraná. Dolly Guaraná Diet. Fanta Laranja. Sprite Zero. Sukita. Oito bebidas e duas substâncias altamente nocivas ao ser humano. Na Coca-cola Zero, está o ciclamato de sódio, um agente químico que reconhecidamente faz mal à saúde. Nos outros sete refrigerantes, está o benzeno, uma substância potencialmente cancerígena.Essa é a mais recente descoberta que vem sendo publicada na mídia e que só agora chega aos ouvidos das maiores vítimas do refrigerante: os consumidores. A pergunta que vem logo à mente é: “por que só agora isso está sendo divulgado?”. E, pior: “se estes refrigerantes fazem tão mal à saúde, por que sua venda é permitida?”.\n" +
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
                "Quando será o fim dessa novela e da venda dos refrigerantes que contém substâncias nocivas à saúde, ninguém sabe. Mas enquanto os fabricantes deixam a ética e o respeito ao cidadão de lado em busca do lucro exacerbado, você tem a liberdade de decidir entre tomar esse veneno ou preservar a qualidade do seu organismo. Agora, é com você!"
        );
        product.setPortionValue(350);
        product.setPortionType("ml");
        product.setCategory("Refrigerante");

    }

    private void populateRecommendations(){
        ImageView avatar1 = (ImageView) findViewById(R.id.avatar_recommends);
        ImageView avatar2 = (ImageView) findViewById(R.id.avatar_recommends2);
        ImageView avatar3 = (ImageView) findViewById(R.id.avatar_recommends3);
        ImageView avatar4 = (ImageView) findViewById(R.id.avatar__dont_recommends);
        ImageView avatar5 = (ImageView) findViewById(R.id.avatar__dont_recommends2);
        ImageView avatar6 = (ImageView) findViewById(R.id.avatar__dont_recommends3);
        Picasso.with(this).load("http://static1.squarespace.com/static/52ce4f15e4b08e540c17fd95/52f176f1e4b0fcddda269f17/533e4d59e4b0d9e2fee883fd/1396591961685/people-faces-50.jpg").into(avatar1);
        Picasso.with(this).load("http://mozina.ru/images/stories/partners/mozina_partners.jpg").into(avatar2);
        Picasso.with(this).load("http://apkspecialistemmen.nl/wp-content/uploads/2016/07/2-1.png").into(avatar3);
        Picasso.with(this).load("https://encrypted-tbn2.gstatic.com/images?q=tbn:ANd9GcQ2o4R6eiXG7A18KPFAZqvxDdAfK937tjH9mgsDr0JibUvuEVJE0g").into(avatar4);
        Picasso.with(this).load("http://i2.ypcdn.com/radiant/radiant_assets_158138_Sara-Bendrick-640-360.jpg").into(avatar5);
        Picasso.with(this).load("https://encrypted-tbn3.gstatic.com/images?q=tbn:ANd9GcRz5dWOnYya8Mb6la29zLgbg825H_4P-zFwXPkYXPguYPUngrEM4Q").into(avatar6);
    }

    private void populateIngredientList() {
        //Parse the Ingredients of the Product
        Ingredient i8 = new Ingredient(0, "Aspartame", "mg", 12, 2);
        ingredients.add(i8);
        Ingredient i = new Ingredient(1, "Acesulfame de potásio", "mg", 15, 2);
        ingredients.add(i);
        Ingredient i2 = new Ingredient(2, "Acidulante ácido fosfórico", "g", 85, 2);
        ingredients.add(i2);
        Ingredient i3 = new Ingredient(3, "Edulcorantes ciclamato de sódio", "ml", 27, 2);
        ingredients.add(i3);
        Ingredient i4 = new Ingredient(4, "Corante caramelo IV", "ml", 946, 5);
        ingredients.add(i4);
        Ingredient i5 = new Ingredient(5, "Aroma natural", "ml", 946, 5);
        ingredients.add(i5);
        Ingredient i6 = new Ingredient(6, "Extrato de noz de cola", "ml", 71, 3);
        ingredients.add(i6);
        Ingredient i7 = new Ingredient(7, "Água gaseificada", "gal", 2, 3);
        ingredients.add(i7);
        Ingredient i9 = new Ingredient(8, "Conservador benzoato de sódio", "*", 0, 3);
        ingredients.add(i9);
        Ingredient i10 = new Ingredient(9, "Regulador de acidez citrato de sódio", "*", 2, 3);
        ingredients.add(i10);


        ProductIngredientListAdapter adapter = new ProductIngredientListAdapter(this, R.layout.listview_product_ingredient, ingredients);
        ListView list = (ListView) findViewById(R.id.product_ingredients_list);
        list.setAdapter(adapter);

        list.setOnTouchListener(new View.OnTouchListener() {
            // Setting on Touch Listener for handling the touch inside ScrollView
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int action = event.getAction();
                switch (action) {
                    case MotionEvent.ACTION_DOWN:
                        // Disallow ScrollView to intercept touch events.
                        v.getParent().requestDisallowInterceptTouchEvent(true);
                        break;

                    case MotionEvent.ACTION_UP:
                        // Allow ScrollView to intercept touch events.
                        v.getParent().requestDisallowInterceptTouchEvent(false);
                        break;
                }

                // Handle ListView touch events.
                v.onTouchEvent(event);
                return true;
            }
        });

        list.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1,int position, long arg3)
            {
                Intent intent = new Intent(ProductActivity.this, IngredientActivity.class);
                intent.putExtra("ingredient_id", ingredients.get(position).getId());
                startActivity(intent);
            }
        });


    }

    public void setGroupData(){
        //TODO: Pegar os grupos que o usuário está subscrito
        groupItem.add("Alimentação Saudável");
        groupItem.add("Diabetes");
        groupItem.add("Hipertensão");
        groupItem.add("Boa Forma");
    }

    public void setChildGroupData() {
        //TODO: Pegar os dados abaixo de acordo, por enquanto só adicionando aleatorio
        int c=0;
        while (c<groupItem.size()){
            ArrayList<Ingredient> ingredientsChild = new ArrayList<>();
            ingredientsChild.add(ingredients.get(0));
            ingredientsChild.add(ingredients.get(3));
            childItem.add(ingredientsChild);
            c++;
        }
    }

    public void setGroupAdapter(){
        GroupListAdapter mGroupAdapter = new GroupListAdapter(groupItem, childItem);
        mGroupAdapter
                .setInflater(
                        (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE),
                        this);
        expandableList.setAdapter(mGroupAdapter);
        expandableList.setOnChildClickListener(this);

        expandableList.setOnTouchListener(new View.OnTouchListener() {
            // Setting on Touch Listener for handling the touch inside ScrollView
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int action = event.getAction();
                switch (action) {
                    case MotionEvent.ACTION_DOWN:
                        // Disallow ScrollView to intercept touch events.
                        v.getParent().requestDisallowInterceptTouchEvent(true);
                        break;

                    case MotionEvent.ACTION_UP:
                        // Allow ScrollView to intercept touch events.
                        v.getParent().requestDisallowInterceptTouchEvent(false);
                        break;
                }

                // Handle ListView touch events.
                v.onTouchEvent(event);
                return true;
            }
        });
    }

    private void populateCommentView() {
        ArrayAdapter<Comment> adapter = new CommentListAdapter(this, R.layout.listview_comments, commentList);
        ListView list = (ListView) findViewById(R.id.commentListView);
        list.setAdapter(adapter);

        list.setOnTouchListener(new View.OnTouchListener() {
            // Setting on Touch Listener for handling the touch inside ScrollView
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int action = event.getAction();
                switch (action) {
                    case MotionEvent.ACTION_DOWN:
                        // Disallow ScrollView to intercept touch events.
                        v.getParent().requestDisallowInterceptTouchEvent(true);
                        break;

                    case MotionEvent.ACTION_UP:
                        // Allow ScrollView to intercept touch events.
                        v.getParent().requestDisallowInterceptTouchEvent(false);
                        break;
                }

                // Handle ListView touch events.
                v.onTouchEvent(event);
                return true;
            }
        });
    }

    private void populateCommentList() {
        //int id, String username, int avatarResourceID, String date, String comment, int rating
        commentList.add(new Comment(1, "João Felipe", 0, "http://i.telegraph.co.uk/multimedia/archive/03249/archetypal-male-fa_3249635c.jpg", "01/07/2016", "Alguem pode me recomendar um substituto para refrigerante? Eu meio que sou viciado.", 2 ));
        commentList.add(new Comment(2, "Maria Claudia", 0, "http://images.agoramedia.com/everydayhealth/gcms/health-benefits-of-smiling-07-pg-full.jpg", "01/07/2016", "Eu vi em algum lugar que suco de saquinho pode ser pior do que o proprio refrigerante", 5 ));
        commentList.add(new Comment(3, "João Felipe", 0, "http://i.telegraph.co.uk/multimedia/archive/03249/archetypal-male-fa_3249635c.jpg", "01/07/2016", "@Maria Claudia, Sim tbm vi, essa nóticia está aqui no BeHappy, salvei até nos favoritos.", 6 ));
        commentList.add(new Comment(4, "Maria Claudia", 0, "http://images.agoramedia.com/everydayhealth/gcms/health-benefits-of-smiling-07-pg-full.jpg", "01/07/2016", "Bom saber! Vou procura-la.", 8 ));
    }

    @Override
    public boolean onChildClick(ExpandableListView parent, View v,
                                int groupPosition, int childPosition, long id) {
        Toast.makeText(this, "Clicked On Child",
                Toast.LENGTH_SHORT).show();
        return true;
    }

    private void initMenuFragment() {
        MenuParams menuParams = new MenuParams();
        menuParams.setActionBarSize((int) getResources().getDimension(R.dimen.tool_bar_height));
        menuParams.setMenuObjects(getMenuObjects());
        menuParams.setClosableOutside(false);
        mMenuDialogFragment = ContextMenuDialogFragment.newInstance(menuParams);
        mMenuDialogFragment.setItemClickListener(this);
        mMenuDialogFragment.setItemLongClickListener(this);
    }

    private void initToolbar() {
        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);
        TextView mToolBarTextView = (TextView) findViewById(R.id.text_view_toolbar_title);
        setSupportActionBar(mToolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setHomeButtonEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }
        mToolbar.setNavigationIcon(R.drawable.btn_back);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        mToolBarTextView.setText("Samantha");
    }

    private List<MenuObject> getMenuObjects() {
        // You can use any [resource, bitmap, drawable, color] as image:
        // item.setResource(...)
        // item.setBitmap(...)
        // item.setDrawable(...)
        // item.setColor(...)
        // You can set image ScaleType:
        // item.setScaleType(ScaleType.FIT_XY)
        // You can use any [resource, drawable, color] as background:
        // item.setBgResource(...)
        // item.setBgDrawable(...)
        // item.setBgColor(...)
        // You can use any [color] as text color:
        // item.setTextColor(...)
        // You can set any [color] as divider color:
        // item.setDividerColor(...)

        List<MenuObject> menuObjects = new ArrayList<>();

        MenuObject close = new MenuObject();
        close.setResource(R.drawable.icn_close);
        close.setBgResource(R.color.colorPrimaryDark);

        MenuObject send = new MenuObject("Send message");
        send.setResource(R.drawable.icn_1);
        send.setBgResource(R.color.colorWhite);
        send.setMenuTextAppearanceStyle(R.style.TextViewStyle);


        MenuObject like = new MenuObject("Like profile");
        Bitmap b = BitmapFactory.decodeResource(getResources(), R.drawable.icn_2);
        like.setBitmap(b);
        like.setBgResource(R.color.colorPrimaryDark);
        like.setMenuTextAppearanceStyle(R.style.TextViewStyle);


        MenuObject addFr = new MenuObject("Add to friends");
        BitmapDrawable bd = new BitmapDrawable(getResources(),
                BitmapFactory.decodeResource(getResources(), R.drawable.icn_3));
        addFr.setDrawable(bd);
        addFr.setBgResource(R.color.colorWhite);
        addFr.setMenuTextAppearanceStyle(R.style.TextViewStyle);


        MenuObject addFav = new MenuObject("Add to favorites");
        addFav.setResource(R.drawable.icn_4);
        addFav.setBgResource(R.color.colorPrimaryDark);
        addFav.setMenuTextAppearanceStyle(R.style.TextViewStyle);


        MenuObject block = new MenuObject("Block user");
        block.setResource(R.drawable.icn_5);
        block.setBgResource(R.color.colorWhite);
        block.setMenuTextAppearanceStyle(R.style.TextViewStyle);


        menuObjects.add(close);
        menuObjects.add(send);
        menuObjects.add(like);
        menuObjects.add(addFr);
        menuObjects.add(addFav);
        menuObjects.add(block);
        return menuObjects;
    }

    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_lib, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.context_menu:
                if (fragmentManager.findFragmentByTag(ContextMenuDialogFragment.TAG) == null) {
                    mMenuDialogFragment.show(fragmentManager, ContextMenuDialogFragment.TAG);
                }
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if (mMenuDialogFragment != null && mMenuDialogFragment.isAdded()) {
            mMenuDialogFragment.dismiss();
        } else {
            finish();
        }
    }

    @Override
    public void onMenuItemClick(View clickedView, int position) {
        Toast.makeText(this, "Clicked on position: " + position, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onMenuItemLongClick(View clickedView, int position) {
        Toast.makeText(this, "Long clicked on position: " + position, Toast.LENGTH_SHORT).show();
    }

/*
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);

        if (init) return;
        init = true;

        Drawable[] subButtonDrawables = new Drawable[6];
        int[] drawablesResource = new int[]{
                R.drawable.like,
                R.drawable.mark,
                R.drawable.search,
                R.drawable.settings,
                R.drawable.info,
                R.drawable.heart
        };
        for (int i = 0; i < 6; i++)
            subButtonDrawables[i] = ContextCompat.getDrawable(this, drawablesResource[i]);

        String[] subButtonTexts = new String[]{"Favoritos", "Notícias", "Procurar", "Configurações", "Informações", "Comunidade"};

        int[][] subButtonColors = new int[6][2];
        for (int i = 0; i < 6; i++) {
            subButtonColors[i][1] = ContextCompat.getColor(this, R.color.colorAccent);
            subButtonColors[i][0] = Util.getInstance().getPressedColor(subButtonColors[i][1]);
        }

        // this is an example to show how to use the builder
        new BoomMenuButton.Builder()
                // set all sub buttons with subButtons method
                //.subButtons(subButtonDrawables, subButtonColors, subButtonTexts)
                // or add each sub button with addSubButton method
                .addSubButton(this, R.drawable.like, subButtonColors[0], "Favoritos")
                .addSubButton(this, R.drawable.info, subButtonColors[1], "Informações")
                .addSubButton(this, R.drawable.heart, subButtonColors[4], "Comunidade")
                .addSubButton(this, R.drawable.mark, subButtonColors[5], "Notícias")
                .addSubButton(this, R.drawable.search, subButtonColors[2], "Procurar")
                .addSubButton(this, R.drawable.settings, subButtonColors[3], "Configurações")
                .frames(80)
                .duration(800)
                .delay(100)
                .showOrder(OrderType.RANDOM)
                .hideOrder(OrderType.RANDOM)
                .button(ButtonType.CIRCLE)
                .boom(BoomType.PARABOLA_2)
                .place(PlaceType.CIRCLE_6_4)
                .showMoveEase(EaseType.EaseOutBack)
                .hideMoveEase(EaseType.EaseOutCirc)
                .showScaleEase(EaseType.EaseOutBack)
                .hideScaleType(EaseType.EaseOutCirc)
                .rotateDegree(720)
                .showRotateEase(EaseType.EaseOutBack)
                .hideRotateType(EaseType.Linear)
                .autoDismiss(true)
                .cancelable(true)
                .dim(DimType.DIM_6)
                .clickEffect(ClickEffectType.RIPPLE)
                .boomButtonShadow(Util.getInstance().dp2px(2), Util.getInstance().dp2px(2))
                .subButtonsShadow(Util.getInstance().dp2px(2), Util.getInstance().dp2px(2))
                .subButtonTextColor(Color.BLACK)
                .onBoomButtonBlick(null)
                .animator(null)
                .onSubButtonClick(null)
                // this only work when the place type is SHARE_X_X
                .shareStyle(0, 0, 0)
                .init(boomMenuButton);
    }*/

}
