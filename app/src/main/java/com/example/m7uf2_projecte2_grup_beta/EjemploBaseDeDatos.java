package com.example.m7uf2_projecte2_grup_beta;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import com.google.firebase.firestore.Blob;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class  EjemploBaseDeDatos extends AppCompatActivity {
    private Button btInsertar,btEsculturas,btArtista;
    private TextView tvContenido;
    FirebaseFirestore db;
    private Spinner listaDeFotos,listaDeFotosAr;
    private  String fotos,fotosAr;
    private  List<Esculturas> ec;
    private List<Artistas> arts;
    private  int positionEs,positionAr;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ejemplo_base_de_datos);
        btInsertar = findViewById(R.id.btInsertar);
        btArtista = findViewById(R.id.btArtistas);
        btEsculturas = findViewById(R.id.btEsculturas);
        tvContenido =findViewById(R.id.tvContenido);


        //spinner que té tipus de permisos segons el xml de la llista del permís  i l'usuari pot decidir quin utilitzar.
        ArrayAdapter<CharSequence> adp = ArrayAdapter.createFromResource(this,R.array.opciones, android.R.layout.simple_spinner_item);
        listaDeFotos =findViewById(R.id.listadefotos);
        listaDeFotos.setAdapter(adp);
        listaDeFotos.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String[] arryfotos = getResources().getStringArray(R.array.opciones);
                fotos = arryfotos[position];
                positionEs = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //spinner que té tipus de permisos segons el xml de la llista del permís  i l'usuari pot decidir quin utilitzar.
        ArrayAdapter<CharSequence> adp2 = ArrayAdapter.createFromResource(this,R.array.opciones02, android.R.layout.simple_spinner_item);
        listaDeFotosAr =findViewById(R.id.listadefotosar);
        listaDeFotosAr.setAdapter(adp2);
        listaDeFotosAr.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String[] arryfotos = getResources().getStringArray(R.array.opciones02);
                fotosAr = arryfotos[position];
                positionAr = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        ec = Arrays.asList(new Esculturas("E01","Sisif","Monistrol de Calders","1968","Pedra calisa de Colmenar","180cm","190cm","8.000kg","A01"),
                new Esculturas("E02","La Família","Monistrol de Calders","1962","Pedra calisa","185cm","282cm","","A02")
                ,new Esculturas("E03","Monòlit","Monistrol de Calders","1968","Pedra Sorrenca","516cm","70cm","4.600kg","A03"),
                new Esculturas("E04","Ballant amb les onades","Monistrol de Calders","1975","Pedra d’Ulldecona","280cm","140cm"," 7500kg","A04"),
                new Esculturas("E05","Superfície esfèrica","Monistrol de Calders","1970","Aliatge d’alumini i magnesi",":260cm","160cm","","A05")
                ,
                new Esculturas("E06","Cinta esfèrica","Monistrol de Calders","1970","Acer inoxidable i ferro","250cm","150cm","","A06")
                ,
                new Esculturas("E07","Tocho","Monistrol de Calders","1962","Xapa de ferro i soldadura","327cm","86cm","","A07")
                ,
                new Esculturas("E08","Santa Teresa","Monistrol de Calders","1965","Pedra de Mingorria","250cm","100cm","8000kg","A08")
                ,
                new Esculturas("E09","Xiquets de Valls","Monistrol de Calders","1968","Pedra del Mèdol","530cm","110cm","7900kg","A09")
                ,
                new Esculturas("E10","Joventut","Monistrol de Calders","1963","Marbre","205cm","110cm","2000kg","A10")
                ,
                new Esculturas("E11","Maternidad peruana","Monistrol de Calders","1965","Pedra sorrenca","87cm","73cm","","A11")
                ,
                new Esculturas("E12","Cangur","Monistrol de Calders","1963","Pedra sorrenca","110cm","60cm","","A09")
                ,
                new Esculturas("E13","Gat","Monistrol de Calders","1970","Pedra Sénia, o d’Ulldecona","85cm","45cm","","A17")
                ,
                new Esculturas("E14","Pingüí","Monistrol de Calders","1970","Pedra sorrenca de Monistrol","150cm","50cm","","A15")
                ,
                new Esculturas("E15","Gos dogo","Monistrol de Calders","1970","Pedra sorrenca de Monistrol","150cm","50cm","","A15")
                ,
                new Esculturas("E16","Girafa","Monistrol de Calders","1970","Pedra sorrenca de Monistrol","160cm","100cm","","A15")
                ,
                new Esculturas("E17","Ós polar","Monistrol de Calders","1960","Pedra sorrenca","140cm","110cm","","A18")
                ,
                new Esculturas("E18","Porc Senglar","Monistrol de Calders","","Feta des d’un motlle","50cm","70cm","","A15")
                ,
                new Esculturas("E19","Dona asseguda","Monistrol de Calders","1968","Marbre cristal·litzat de Portugal","124cm","195cm","3600kg","A09")
                ,
                new Esculturas("E20","Dama","Monistrol de Calders","1965","Pedra sorrenca","110cm","172cm","6500kg","A12")
                ,
                new Esculturas("E21","Cercant l’estel","Monistrol de Calders","1963","Bronze i ferro forjat","185 cm","282 cm","","A13")
                ,
                new Esculturas("E22","La Venus del mar","Monistrol de Calders","1968","Bronze","274cm","115cm","","A14")
                ,
                new Esculturas("E23","Música i vent","Monistrol de Calders",""," Pedra sorrenca","257cm","88cm","12000kg","A02")
                ,
                new Esculturas("E24","Toscadeiro","Monistrol de Calders","1973","Pedra sorrenca de Monistrol de Calders","250cm","290cm","8700kg","A15")
                ,
                new Esculturas("E25","Tronc, art africà","Monistrol de Calders","1974","Fusta","230cm","120cm","","A03"));

        arts = Arrays.asList(
                new Artistas("A01", "César", "Montaña García", "(1928 - 2000) Nascut a el 1928 a Vegadeo (Astúries) Va estudiar a l’Escuela Superior de Bellas Artes de Madrid. Posteriorment, va residir a Itàlia, des d’on, en constant formació, va fer viatges d’estudis per aquell país, Grècia, França, Bèlgica, Holanda, Alemanya, Àustria i Anglaterra. " +
                        "Va guanyar nombrosos premis i guardons i ha fet exposicions, individuals i col·lectives arreu del món. A partir del 1960 passà a viure a Madrid, on instal·là el seu taller, i on morí l’any 2000.", "És un escultor d’arrels classicitzants, però que amb el pas del temps ha anat evolucionant, a través de l’experimentació en materials, formats i volums, cap a l’abstracció, desdibuixant les formes fins a fer-les de vegades difícils de " +
                        "reconèixer-les. Ha treballat principalment amb la pedra i el metall, sobretot bronze.",
                        Arrays.asList(ec.get(0))),
                new Artistas("A02", "Sebastià", "Badia Cerdà", "(1916 - 2009) Nascut a Caldes de Montbui (Vallès Oriental) l’any 1916, fou un escultor bàsicament autodidacte, atès que la Guerra Civil interrompé radicalment la seva formació acadèmica. Començà treballant en el camp de la imatgeria religiosa, fins que el 1943 es convertí en deixeble de Manolo Hugué, i es mantingué al seu costat fins a la seva mort, dos anys més tard. Fou un escultor que actuà molt en la reconstrucció d’esglésies posterior al final de " +
                        "la Guerra Civil.", "Amb un estil bàsicament acadèmic, la influència de Manolo Hugué el féu evolucionar cap a un estil més fred i volumètric.",
                        Arrays.asList(ec.get(1)))
                ,
                new Artistas("A03", "Charles Henry", "Collet Colomb", "(1902-1983) Nascut a Onex (Cantó de Ginebra, Suïssa) l’any 1902, es formà a Ginebra i l’any 1923 es traslladava a Barcelona, on completà els estudis al taller dels Corberó i a l’orfebreria de Ramon Sunyer. El 1929 era professor a l’Escola Massana. Del 1936 al 1939 tornà a Suïssa, però acabada la guerra s’instal·là novament a Barcelona, on fou un artista present a totes les manifestacions artístiques de l’època, juntament amb la seva dona, la pintora Eugénie Milloud (Ninon Collet). Una de les seves obres més conegudes és una imatge de l’atri de la basílica de Montserrat. " +
                        "Morí a Barcelona el 1983.", "Fou un autor molt prolífic, va participar en els seus inicis barcelonins del Noucentisme tardà, i posteriorment va evolucionar cap a formes més estilitzades i esquemàtiques, de superficies rugoses i estructura angulosa, que, utilitzant el plom, el ferro, etc, tendeixen cap a l’abstracció.",
                        Arrays.asList(ec.get(2),ec.get(22),ec.get(24)))
                ,
                new Artistas("A04", "Joan", "Serafini Masdeu", "(1931-2017) Nascut a Valls (l’Alt Camp) el 1931, prengué contacte amb l’escultura a les classes d’art de l’Escola del Treball de la seva vila natal; després d’uns primers anys de cursar dibuix, pintura i escultura amb professors locals, els primers aprenentatges en l’escultura li arribaren de les mans del mestre escultor Josep Busquets, al costat del qual treballà durant 7 anys en la restauració del retaule major de Sant Joan de Valls, època en què s’integrà en el grup Un nus, format per escultors de l’Alt Camp. Participà en nombroses exposicions, fins que l’any 1954 es traslladà a Barcelona, " +
                        "sempre sota l’empara del seu mestre, i completà la seva formació artística en el Cercle Artístic de Sant Lluc. Morí a Valls el 2017.", "El seu estil, que partia del figurativisme classicitzant, propi de retaules i imatges religioses, anà evolucionant a la recerca de l’intent de plasmar el moviment a les seves escultures, bastants de les quals estan " +
                        "clarament dins del concepte de l’escultura cinegètica.",
                        Arrays.asList(ec.get(3)))
                ,
                new Artistas("A05", "Vicente", "Larrea Gayarre", "(1934-2000) Nascut a Bilbao (País Basc) el 1934, fill de José Larrea Echaniz i nét de Vicente Larrea Aldama, tots dos escultors amb taller propi. L’avi, l’iniciador del taller, s’havia format a París amb Rodin, i el seu taller, continuat pel seu fill, gaudia d’un gran prestigi. Inicià la seva formació artística en el taller familiar, i continuà els estudis a l’Escuela de Artes y Oficios de Atxuri, després en el Museo de Reproducciones de Bilbao i, encara, amb l’escultor Raymond Dubois, a Solesmes (França). La seva formació en el taller familiar el posà des de molt jove en contacte amb el món de les escultures públiques, " +
                        "moltes de gran complexitat tècnica; a més, és Enginyer Tècnic de Mines i Siderúrgia. A partir del 1960 passà a viure a Madrid, on instal·là el seu taller, i on morí l’any 2000.", "Tot i els seus començaments dins d’una estètica classicitzant, la seva evolució el dugué a un total trencament amb les formes clàssiques, fins al punt de tancar el taller familiar, i evolucionar cap a un estil molt personal, experimentant tant en materials com en formes i temàtiques, dins dels corrents avantguardistes bascos contemporanis seus, com Gaur, de Guipúscoa, i Emen, de Bilbao. El conjunt de la seva obra és d’una forta personalitat, difícil d’encasellar, que algun crític ha relacionat amb l’art geomètric abstracte.",
                        Arrays.asList(ec.get(4)))
                ,
                new Artistas("A06", "Enrique", "Salamanca", "(1943) Nascut a Cadis el 1943, aviat passà a residir a Madrid, on encara resideix. Els anys 60 va fer una llarga estada a Eivissa. Pintor i escultor, a més de practicar altres arts manuals, com serigrafia, va estudiar Belles Arts i Delineació a Madrid. En contacte amb els artistes més rellevants dels anys setanta ençà, la seva obra és en contínua experimentació amb tota mena de materials, especialment amb metalls i materials sintètics, i en constant evolució, fins al punt de crear escultures cinètiques (amb moviment, algunes amb motor). També utilitza molt tota mena d’estructures geomètriques, sempre en el món de l’abstracció. " +
                        "És un dels artistes més rellevants dels darrers anys del segle XX i els primers del XXI.", "",
                        Arrays.asList(ec.get(5)))
                ,
                new Artistas("A07", "Javier", "Sauras Viñuales", "(1943) Nascut a Osca (l’Aragó) el 1944, es formà a l’Escola Superior de Belles Arts Sant Jordi, de Barcelona, i té una àmplia carrera com a escultor i com a docent d’aquesta disciplina. Fou professor dels departaments d’escultura de la Facultad de Bellas Artes de la Universidad del País Vasco i de la Facultad de Bellas Artes de la Universidad Complutense de Madrid. És membre de la Real Academia de Nobles y Bellas Artes de San Luis, de Saragossa. " +
                        "El seu darrer destí docent fou la direcció de l’Escuela Superior de Conservación y Restauración de Bienes Culturales de Aragón.", "Ha mantingut sempre un lligam amb la figuració, i és un dels màxims representants del formalisme peninsular de les dècades dels seixanta i setanta del segle XX. Alhora, però, amb criteris propis molt independents, que l’han dut a experimentar amb materials, formes i volums, arribant en alguns casos a l’abstracció. Mai no s’ha adscrit, però, a cap grup artístic o escola.",
                        Arrays.asList(ec.get(6)))
                ,
                new Artistas("A08", "Tomás", "Crespo Rivera", "(1932) Nat a Zamora (Lleó) el 1932, va estudiar a l’Escuela de Bellas Artes de Zamora, i completà la seva formació acadèmica a Madrid, a l’Escuela de Bellas Artes de San Fernando. A començament dels anys seixanta, es va traslladar a Barcelona, on entrà en contacte amb el Cercle Liceu Francès i el crític i pintor José María de Sucre. Retornà a Zamora el 1962, on va establir el seu estudi. Entre el 1983 i el 1991, es dedicà a la docència, fent classes de modelat a l’Escuela de Artes y Oficios de Zamora. El 1993 va centrar la seva activitat en la creació de dissenys i en decoració, dirigint l’empresa de la qual és copropietari. " +
                        "Des del 1999 fins als nostres dies es dedica plenament a l’escultura.", "Sobre la base del figurativisme classicitzant, Tomás Crespo ha experimentat sobre formes, volums, textures i materials a la recerca d’una expressivitat que connecti amb l’entorn social, iniciant un camí cap a l’abstracció, simplificant línies i volums, sobretot. Ha treballat amb fusta, pedra i bronze.",
                        Arrays.asList(ec.get(7)))
                ,
                new Artistas("A09", "Josep Maria ", "Brull i Pagès", "(1907-1995) Nascut a Ascó (la Ribera d’Ebre) l’any 1907, va residir a Tivissa (la Ribera d’Ebre), Ripollet (el Vallès Occidental) i Olot (la Garrotxa), i tornà a Ripollet, on ja s’establí els darrers 50 anys de la seva vida, i on morí el 1995. Era mestre, a més de ceramista i escultor, i el 1940 fou represaliat pel franquisme; acusat de catalanista i separatista, fou desposseït del títol de mestre. Estigué molt lligat als cercles artístics i culturals de la ciutat de Sabadell, i " +
                        "fou professor de l’Escola Industrial i d’Arts i Oficis de la capital vallesana i de l’escola de la seva muller, la pintora Encarnació Braut, a Ripollet.", "Sempre recercant un estil propi, parteix de l’academicisme i del costumisme, principalment a partir de la vida rural i el retrat de les maternitats, va evolucionant cap a una escultura geomètrica i de formes planes d’abstracció del figurativisme.",
                        Arrays.asList(ec.get(8),ec.get(11),ec.get(18)))
                ,
                new Artistas("A10", "Margarida", "Sans i Jordi", "(1911-2006) Nascuda a Barcelona (el Barcelonès) l’any 1911, es formà amb els escultors Àngel Ferrant, qui li va fer descobrir les eines i les tècniques, i Jordi Llimona, de qui es convertí en deixeble i, segons el mateix Llimona, en sucessora seva. A la mort del seu mestre, el 1934, viatjà cinc anys per Europa (Londres, Brussel·les, Itàlia, França, Grècia i Àsia Menor), i encara féu una estada a París, on rebé classes de Charles Despiau. Va treballar sobretot en dos camps: imatgeria religiosa i nu femení. Morí a Barcelona el 2006.",
                        "",Arrays.asList(ec.get(9)))
                ,
                new Artistas("A11", "Jacinto", "Bustos Vasallo", "(1922-1987) Nascut a Aldeanueva de Figueroa (Salamanca) l’any 1922, en una família humil, comença la seva activitat com a sabater, però el 1946 anava a Salamanca on, després d’aprenentatges amb artistes com Francisco González Macías, aconseguí entrar a l’Escola d’Arts i Oficis. Després de guanyar diversos premis importants, se n’anà de Salamanca el 1954, de primer cap a Madrid i després cap a Barcelona, on es convertí en deixeble de Josep Clarà i va rebre classes a l’Escola d’Arts i Oficis. El 1956 marxà cap a París, i començà una ratxa de nombrosos premis internacionals. Morí a Barcelona el 1987.",
                        "Autor d’una obra de tall classicitzant, figurativa, rebé nombrosos encàrrecs d’institucions diverses.",
                        Arrays.asList(ec.get(10)))
                ,
                new Artistas("A12", "José", "Carrilero Gil", "(1928) Nascut a Caravaca de la Cruz (Múrcia) l’any 1928, com a fill de funcionari, als tres anys se n’anava d’aquella ciutat i començà a residir a Múrcia. Començà a prendre contacte amb l’art a la Sociedad de Amigos del Pais de Múrcia, i als 13 anys entrava a l’Escuela de Artes y Oficios de la ciutat murciana, on diversos professors li varen inculcar diversos aspectes artístics que li foren fonamentals per al seu futur. Als 24 anys ingressà en la Real Academia de Bellas Artes de San Fernando de Madrid, on completà la seva formació acadèmica. Al final dels seus estudis guanyà una pensió per a l’Academia de España a Roma, on va estar tres anys, que suposaren un gir decisiu en la seva trajectòria artística i on formà el grup de Los seis escultores. A més, els estius corresponents viatjà per tota Itàlia, i visità els grans museus de França, Bèlgica, Holanda i Alemanya. Al seu retorn a Madrid, treballà a la Fábrica Nacional de Moneda y Timbre, on dissenyà una sèrie de medalles que tingueren una gran repercussió internacional. Després, entrà com a professor de dibuix a l’Escuela de Artes Aplicadas y Oficios Artísticos de Madrid, on estigué quinze anys, catorze dels quals com a director de secció. Acadèmic de la Real Academia de Bellas Artes de Santa María de la Arrixaca de Murcia des del 2003, en el seu poble natal es creà el 2010 el Museo Carrilero, que conté 90 obres seves de pintura i escultura.",
                        "De formació inicialment clàssica, l’estada a Roma el dugué cap a l’estil conegut com a Nova figuració, o neofiguratisme, molt influïda per elements naturalistes. Després, però, es va anar decantant cap a la figuració humana, sobretot femenina, tractada de manera informal i expressionista. Treballa sobretot en obres de petit i mitjà format, en les qual predominen els volums imaginatius, amb predomini dels de tall gruixut i forma hercúlia.",
                        Arrays.asList(ec.get(19)))
                ,
                new Artistas("A13", "Emili", "Colom i Comerma", "(1924-2007) Nascut a Barcelona el 1924, fill i nét d’escultors, la seva fa- mília fou el primer lloc d’apre- nentatge. Estudià a Llotja i després a l’Escola Superior de Belles Arts de Sant Jordi. Amb 19 anys obtingué un premi de final de curs que consistia en una exposició individual al Palau de la Virreina, en la qual va exposar 35 escultures fetes amb diversos materials. Poc després rebia el primer encàrrec important i, com era habitual, convidà els seus condeixebles a un sopar, del qual sorgí el grup Betepocs, que dona- ria molt a parlar per les seves exposicions itinerants i per les seves tertúlies de cafè. Per qüestions econòmiques, va haver de deixar els estudis, i entrà a treballar, successivament, d’aprenent en una fundició artística, en un taller de picapedrer, i en un altre on feien altars i retaules. Totes aquestes feines contribuïren decisivament en la seva trajectòria com a escultor. Els primers encàrrecs que va rebre foren de caràcter religiós, i va elaborar algunes escultures de gran format per a esglésies i santuaris. Més endavant, anà incorporant a la seva trajectòria d’altres temàtiques. De caràcter inquiet, treballà amb diferents tipus de materials: pedra, bronze, ferro... Com a expert artístic, va treballar en el Servei de catalogació i conservació de monuments de la Diputació de Barcelona, on va participar fins a la jubilació en la restauració de nombroses peces d’art, des de la Marededéu de Rus, del segle XI, fins a la Mare de Déu de la Victòria, del XVII. Els darrers anys de la seva vida estigué creant en el seu taller de Bellaterra, on va morir el 2007.",
                        "Sense deixar mai el figurativisme, Colom evolucionà des d’un art classicitzant, en els seus inicis, a l’experimentació a partir de l’estilització i fins i tot trencament de les formes clàssiques, com el collage fet en ferro d’una imatge d’un cavall. Voluntàriament, però, com ell mateix digué, allunyant-se de les tendències més avançades de l’art.",
                        Arrays.asList(ec.get(20)))
                ,
                new Artistas("A14", "Josep", "Canals Gual", "(1947) Nascut a Sant Cugat del Vallès (Vallès Occidental) l’any 1947, es formà successivament a l’Escola d’Arts i Oficis de la Llotja de Barcelona, a l’Escola Massana, a l’Escola Internacional de Pintura Mural Contemporània de Sant Cugat del Vallès i a la Facultat de Belles Arts de la Universitat de Barcelona, on obtingué la llicenciatura en l’especialitat d’escultura. Fou professor i director de l’Escola de Belles Arts de Sant Cugat del Vallès i d’una escola privada propera a Sant Cugat del Vallès, on ha residit sempre i on ha estat sempre un activíssim actor i promotor cultural. Ha obtingut nombrosos premis i guardons, i el 2011 fou elegit acadèmic corresponent pel seu poble a la Reial Acadèmia Catalana de Belles Arts de Sant Jordi. És també escriptor.",
                        "",Arrays.asList(ec.get(21)))
                ,
                new Artistas("A15", "Josep", "Sobrado Balboa", "(1944) Nascut a Villádiga – Láncara (Lugo) el 1944, resideix a Sabadell des del 1964. Estudià Belles Arts a la ciutat vallesana, on fou deixeble de Josep Maria Brull, autor de Dona asseguda, Xiquets de Valls i Cangur, presents en aquest mateix museu. És de destacar que Toscadeiro fou realitzada a Monistrol de Calders, amb el seu autor hostatjat a casa d’en Fèlix Estrada i Saladich, en un espai al carrer davant mateix de Ca l’Estrada (número 7 del carrer del Ferrer, actualment de Fèlix Estrada i Saladich).",
                        "Autodidacta i amb estil propi, parteix del figurativisme i comença un camí cap a l’abstracció, que fa que part de les seves escultures desdibuixin les línies estrictament figurativistes.",
                        Arrays.asList(ec.get(23),ec.get(13),ec.get(14),ec.get(15),ec.get(17)))
                ,
                new Artistas("A17", "Tomàs", "Bel Sabatés", "",
                        "",Arrays.asList(ec.get(13)))
                ,
                new Artistas("A18", "Josep", "Garriga i Sauló", "",
                        "",Arrays.asList(ec.get(17)))

        );

        db=FirebaseFirestore.getInstance();

    }

    public void  ConsultaEscultura(View v){
        db.collection("Esculturas")
                .document("E01")
                .get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        if(documentSnapshot.exists()) {
                            Esculturas e1 = documentSnapshot.toObject(Esculturas.class);
                            tvContenido.setText(e1.toString());
                        }
                        else{
                            Toast.makeText(EjemploBaseDeDatos.this, "Escultura no existe", Toast.LENGTH_LONG).show();
                        }
                    }
                });

    }

    public void  ConsultaArtistas(View v) {
        db.collection("Artistas")
                .document("A01")
                .get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        if (documentSnapshot.exists()) {
                            Artistas a2 = documentSnapshot.toObject(Artistas.class);
                            tvContenido.setText(a2.toString());
                        } else {
                            Toast.makeText(EjemploBaseDeDatos.this, "Artista no existe", Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }

    public void  InsertacionEscultura(View v){
        Esculturas es1 = ec.get(positionEs);
        try {
            // Consultem la mida del fitxer e_sisif.jpg que tenim guardat a la carpeta assets.
            int mida = (int)EjemploBaseDeDatos.this.getAssets().openFd(fotos.toString()).getLength();
            byte[] buffer = new byte[mida];

            // Preparem un InputStream per poder llegir el contingut de l'arxiu.
            InputStream is =EjemploBaseDeDatos.this.getAssets().open(fotos.toString());

            // Llegim el contingut i tanquem el fitxer.
            is.read(buffer);
            is.close();

            // Carreguem el array de bytes sobre l'objecte alumne, en el seu atribut fotos,
            // convertint l'array de bytes a Blob.
            es1.getFotos().add(Blob.fromBytes(buffer));


        }
        catch (IOException ioe) {

        }


        db.collection("Esculturas")
                .document(es1.getId())
                .set(es1)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void v) {
                        // En cas que la inserció hagi anat bé, no farem res en especial.
                    }
                }).addOnFailureListener(new OnFailureListener() {
                                            @Override
                                            public void onFailure(@NonNull Exception e) {
                                                Toast.makeText(EjemploBaseDeDatos.this, "La inserció ha fallat: " + e.getMessage(), Toast.LENGTH_LONG).show();
                                            }
                                        }

        );
    }
    public void  InsertacionArtista(View v){
        Artistas ar = arts.get(positionAr);
        try {
            // Consultem la mida del fitxer e_sisif.jpg que tenim guardat a la carpeta assets.
            int mida = (int)EjemploBaseDeDatos.this.getAssets().openFd(fotosAr.toString()).getLength();
            byte[] buffer = new byte[mida];

            // Preparem un InputStream per poder llegir el contingut de l'arxiu.
            InputStream is =EjemploBaseDeDatos.this.getAssets().open(fotosAr.toString());

            // Llegim el contingut i tanquem el fitxer.
            is.read(buffer);
            is.close();

            // Carreguem el array de bytes sobre l'objecte alumne, en el seu atribut fotos,
            // convertint l'array de bytes a Blob.
            ar.getFotos().add(Blob.fromBytes(buffer));


        }
        catch (IOException ioe) {

        }


        db.collection("Artistas")
                .document(ar.getId())
                .set(ar)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void v) {
                        // En cas que la inserció hagi anat bé, no farem res en especial.
                    }
                }).addOnFailureListener(new OnFailureListener() {
                                            @Override
                                            public void onFailure(@NonNull Exception e) {
                                                Toast.makeText(EjemploBaseDeDatos.this, "La inserció ha fallat: " + e.getMessage(), Toast.LENGTH_LONG).show();
                                            }
                                        }

        );
    }

}