package com.example.behappy.behappyapp;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.behappy.behappyapp.adapters.CommentListAdapter;
import com.example.behappy.behappyapp.adapters.SimpleNewsListAdapter;
import com.example.behappy.behappyapp.classes.Comment;
import com.example.behappy.behappyapp.classes.Ingredient;
import com.example.behappy.behappyapp.classes.News;
import com.example.behappy.behappyapp.classes.Product;
import com.ramotion.foldingcell.FoldingCell;
import com.yalantis.contextmenu.lib.ContextMenuDialogFragment;
import com.yalantis.contextmenu.lib.MenuObject;
import com.yalantis.contextmenu.lib.MenuParams;
import com.yalantis.contextmenu.lib.interfaces.OnMenuItemClickListener;
import com.yalantis.contextmenu.lib.interfaces.OnMenuItemLongClickListener;

import java.util.ArrayList;
import java.util.List;

import it.gmariotti.cardslib.library.internal.Card;

public class IngredientActivity extends AppCompatActivity implements OnMenuItemClickListener, OnMenuItemLongClickListener {

    ArrayList<Card> cards = new ArrayList<Card>();
    ArrayList<Ingredient> ingredients = new ArrayList<Ingredient>();
    Product product;
    //private BoomMenuButton boomMenuButton;
    private boolean init = false;
    private FragmentManager fragmentManager;
    private ContextMenuDialogFragment mMenuDialogFragment;

    private ArrayList<String> groupItem = new ArrayList<>();
    private ArrayList<Object> childItem = new ArrayList<Object>();

    private List<News> newsFeed = new ArrayList<News>();
    private List<Comment> commentList = new ArrayList<Comment>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getIntent().getExtras();
        Integer ingredientId = bundle.getInt("ingredient_id");
        setTitle("Ingrediente: " + ingredientId);
        setContentView(R.layout.activity_ingredient);

        // prepare elements to display
        fragmentManager = getSupportFragmentManager();
        initMenuFragment();


        //final FoldingCell cell = (FoldingCell) findViewById(R.id.ingredient_info_cell);
        final FoldingCell groupsCell = (FoldingCell) findViewById(R.id.ingredient_groups_cell);
        final FoldingCell commentsCell = (FoldingCell) findViewById(R.id.ingredient_comments_cell);

        ViewHolder viewHolder = new ViewHolder();

        populateCommentList();
        populateCommentView();

        // binding view parts to view holder

        //viewHolder.name = (TextView) cell.findViewById(R.id.ingredient_main_name);
        /*
        viewHolder.innerName = (TextView) cell.findViewById(R.id.ingredient_);
        viewHolder.manufacturer = (TextView) cell.findViewById(R.id.product_title_manufacture_value);
        viewHolder.category = (TextView) cell.findViewById(R.id.product_title_category_value);
        viewHolder.imgAvatar = (ImageView) cell.findViewById(R.id.product_title_avatarImg);
        viewHolder.countryOrigin = (TextView) cell.findViewById(R.id.product_title_origin);
        */

        //cell.setTag(viewHolder);

        /*cell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cell.toggle(false);
            }
        });*/
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
        /*
        viewHolder.name.setText(product.getName());
        viewHolder.innerName.setText(product.getName());
        viewHolder.manufacturer.setText(product.getManufacturer().getName());
        viewHolder.category.setText(product.getCategory());
        viewHolder.imgAvatar.setImageResource(product.getImgResourceID());
        viewHolder.countryOrigin.setText(product.getCountryOrigin());
        //viewHolder.portionType.setText(product.getPortionType());
        //viewHolder.portionValue.setText(String.valueOf(product.getPortionValue()));
        viewHolder.curiosity.setText(product.getCuriosity());
        viewHolder.ingredientQuantity.setText("" + ingredients.size());
        */

        populateNewsFeed();
        populateNewsListView();
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
        mToolBarTextView.setText("");
    }

    private void populateNewsListView() {
        ArrayAdapter<News> adapter = new SimpleNewsListAdapter(this, R.layout.listview_news, newsFeed);
        ListView list = (ListView) findViewById(R.id.ingredient_news_list);
        list.setAdapter(adapter);
    }

    private void populateNewsFeed() {
        News n1 = new News ("PepsiCo elimina aspartame da Diet Pepsi nos Estados Unidos", "http://epocanegocios.globo.com/Inspiracao/Empresa/noticia/2015/08/pepsi-lanca-produto-livre-de-aspartame-nos-eua.html");
        n1.setSubtitle("A nova linha contará com outro tipo de adoçante artificial para tentar aumentar vendas");
        n1.setData("08/09/2015");
        n1.setSource("epocanegocios.globo.com");
        n1.setRating(35);
        newsFeed.add(n1);
        News n2 = new News ("Aspartame", "http://www.cancer.org/cancer/cancercauses/othercarcinogens/athome/aspartame");
        n2.setSubtitle("Aspartame (NutraSweet, Equal, etc) is one of the most common artificial sweeteners in use today. Find out what we know about its safety here.");
        n2.setRating(25);
        n2.setSource("cancer.org");
        newsFeed.add(n2);
        News n3 = new News ("Coca Cola's Low Calorie Beverages Will Kill You Before They Solve Obesity", "http://foodbabe.com/2013/01/25/coca-colas-low-calorie-beverages-will-kill-you-before-they-solve-obesity/");
        n3.setSubtitle("When I saw Coca-Cola’s new anti-obesity ad, my jaw dropped wide open. Yes, you read that right… Coca-Cola is on a new mission to fight the obesity epidemic now, in what I call a desperate attempt to prevent declining sales.");
        n3.setRating(25);
        n3.setSource("Food Babe");
        newsFeed.add(n3);

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
        commentList.add(new Comment(1, "João Felipe", 0, "http://i.telegraph.co.uk/multimedia/archive/03249/archetypal-male-fa_3249635c.jpg", "01/07/2016", "Então refrigerantes com zero calorias acabam não sendo uma solução?.", 2 ));
        commentList.add(new Comment(2, "Maria Claudia", 0, "http://images.agoramedia.com/everydayhealth/gcms/health-benefits-of-smiling-07-pg-full.jpg", "01/07/2016", "Depende do seu regime, esse ingrediente tem seus problemas, mas o excesso de calorias podem ser mais prejudiciais", 5 ));
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

}
