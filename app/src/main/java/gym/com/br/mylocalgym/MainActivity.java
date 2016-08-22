package gym.com.br.mylocalgym;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        //Recupera sessão e checa login
        SessionManager sessionManager = new SessionManager(getApplicationContext());
        sessionManager.checkLogin();

        // Pega da sessão as informações do usuário
        HashMap<String, String> user = sessionManager.getUserDetails();

        // Inicia o header do menu drawer
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);

        View headerView = navigationView.getHeaderView(0);
        TextView emailText = (TextView) headerView.findViewById(R.id.headerEmail);
        TextView apelidoText = (TextView) headerView.findViewById(R.id.headerapelido);

        // Recupera as informações do user e as coloca no header
        apelidoText.setText(user.get(SessionManager.KEY_NAME));
        emailText.setText(user.get(SessionManager.KEY_EMAIL));

        navigationView.setNavigationItemSelectedListener(this);

        // Configurações para mapa
        fragmentManager = getSupportFragmentManager();

        FragmentTransaction transaction = fragmentManager.beginTransaction();

        transaction.add(R.id.conteiner, new MapsFragment(), "MapsFragment");
        transaction.commitAllowingStateLoss();
    }



    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id)
        {
            case R.id.mn_buscar:
                onSearchRequested();
                break;
            case R.id.mn_statusexame:
                break;
            case R.id.mn_favoritos:
                break;
            case R.id.mn_treinos:
                break;
            case R.id.mn_Saldo:
                break;
            case R.id.mn_Recarga:
                break;
            case R.id.mn_Uacadem:
                break;
            case R.id.mn_Utreinos:
                break;
            case R.id.mn_Dpessoais:
                break;
            case R.id.mn_Opesquisa:
                break;
            case R.id.mn_Logoff:
                //chama sessionManager para apagar informações do usuário
                SessionManager sessionManager = new SessionManager(getApplicationContext());
                sessionManager.logoutUser();
                break;
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    //Utilizar para chamar outros fragmentos
    private void mostraFragmento(Fragment fragment, String name){

        FragmentTransaction transaction = fragmentManager.beginTransaction();

        transaction.replace(R.id.conteiner, fragment, name);
        transaction.commit();
    }





}
