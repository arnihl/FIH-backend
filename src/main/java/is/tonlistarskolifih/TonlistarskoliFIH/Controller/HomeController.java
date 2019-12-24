package is.tonlistarskolifih.TonlistarskoliFIH.Controller;

import is.tonlistarskolifih.TonlistarskoliFIH.Entity.*;
import is.tonlistarskolifih.TonlistarskoliFIH.Service.CourseService;
import is.tonlistarskolifih.TonlistarskoliFIH.Service.NewsStoryService;
import is.tonlistarskolifih.TonlistarskoliFIH.Service.TeacherService;
import is.tonlistarskolifih.TonlistarskoliFIH.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.task.TaskExecutionAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

@Controller
public class HomeController {

    UserService userService;
    TeacherService teacherService;
    NewsStoryService newsStoryService;
    CourseService courseService;

    @Autowired
    public HomeController(UserService userService, TeacherService teacherService, NewsStoryService newsStoryService, CourseService courseService) {
        this.userService = userService;
        this.teacherService = teacherService;
        this.newsStoryService = newsStoryService;
        this.courseService = courseService;
    }

    // dökkt protection fyrir kennaramyndir
    // hugsa útí fréttir þegar þær eru orðnar margar talsins.
    // laga lit á hoveri í töflu bóklegra greina
    // bæta við ör á algengar spurningar upp eða niður.
    // Laga kennara admin þannig að myndir séu eins og á kennara cards. eða max size.



    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String Home(Model model){
        model.addAttribute("fihafangar", courseService.findAllFIHCourses());
        model.addAttribute("mitafangar", courseService.findAllMITCourses());
        model.addAttribute("stories", newsStoryService.getNewestStories());
        return "index";
    }

    @RequestMapping("/umsoknir")
    public String Umsoknir(){
        return "umsoknir";
    }

    @RequestMapping("/umskolann")
    public String UmSkolann(){
        return "umskolann";
    }

    @RequestMapping("/stjorn-fih")
    public String StjornFih(){
        return "stjornSkolans";
    }

    @RequestMapping("/nemendafelag-fih")
    public String NemendafelagFih(){
        return "nemendafelagFih";
    }

    @RequestMapping("/jafnrettisaaetlun")
    public String Jafnrettisaaetlun(){
        return "jafnrettisaetlunFIH";
    }

    @RequestMapping("/namsstig")
    public String Namsstig(){
        return "namsstig";
    }

    @RequestMapping("/fraedigreinar")
    public String Fraedigreinar(){
        return "fraedigreinar";
    }


    @RequestMapping(value = "/boklegargreinar", method = RequestMethod.GET)
    public String BoklegarGreinarGet(Model model){
        model.addAttribute("fihafangar", courseService.findAllFIHCourses());
        model.addAttribute("mitafangar", courseService.findAllMITCourses());
        return "boklegargreinar";
    }

    @RequestMapping("/hafasamband")
    public String HafaSamband(){
        return "hafasamband";
    }

    @RequestMapping(value = "/adofinni", method = RequestMethod.GET)
    public String ADofinniGet(Model model){
        model.addAttribute("stories", newsStoryService.getStoriesNewestFirst());
        return "a-dofinni";
    }

    @RequestMapping("/algengarspurningar")
    public String FAQ(){
        return "Algengarspurningar";
    }

    @RequestMapping("/kennarar")
    public String Kennarar(Model model){
        model.addAttribute("teachers", teacherService.findAll());
        return "kennarar";
    }

    @RequestMapping(value = "/kennarar/{id}", method = RequestMethod.GET)
    public String KennariGet(@PathVariable("id") String id, Model model){
        ConcreteTeacher teacher = teacherService.findById(Long.parseLong(id));
        if(teacher == null ) return "redirect:/kennarar";
        model.addAttribute("teacher", teacher);
        return "kennari";
    }

    @RequestMapping("/makedata")
    public String MakeData() {
        userService.save(new User("fihAdmin", "FIHadmin!2#4"));
        teacherService.save(new Teacher("Aðalheiður Þorsteinsdóttir", "AÞ", "allat@simnet.is", "864 9234", "Aðalheiður Þorsteinsdóttir lauk námi frá tónfræðadeild Tónlistarskólans í Reykjavík og organistaprófi frá Tónskóla Þjóðkirkjunnar. Hún hefur starfað sem píanóleikari og organisti með fjölda einsöngvara og kóra. Einnig sinnt útsetningum, kennslu, séð um tölvusetningu nótnabóka og verið í hlutastarfi hjá Íslenskri tónverkamiðstöð sem hefur að geyma nótnasafn íslenskra tónverka.", "Meðleikur", "Adalheidur_Thorsteinsdottir.jpg"), null);
        teacherService.save(new Teacher("Agnar Már Magnússon", "AMM", "agnarmagnusson@internet.is", "695 1496", "Eftir að hafa lokið burtfararprófi frá Tónlistarskóla FÍH kláraði Agnar nám við Conservatorium van Amsterdam og einkanám hjá Larry Goldings í New York. Í New York komst Agnar í kynni við fleiri þekkta tónlistarmenn á sviði djassins en þau kynni leiddu m.a. til útgáfu fyrsta geisladisks hans sem ber nafnið 01. Frá því þá hefur Agnar gefið út fjölda geisladiska til viðbótar. Agnar hefur margoft verið tilnefndur til íslensku tónlistarverðlaunanna og tvisvar hlotið þau. \n" +
                "\n" +
                "Agnar skrifaði heila dagskrá fyrir Stórsveit Reykjavíkur 2010 og var hún flutt á tónleikum það ár. Tvö verkanna voru svo tekin upp og gefin út 2011 á disknum HAK. Sá diskur hlaut íslensku tónlistarverðlaunin sem djass diskur ársins. Agnar hefur starfað með heimsklassa djasstónlistarmönnum. Þar má nefna Bill Stewart, Ben Street, Seamus Blake, Chris Cheek, Ingrid Jensen, Frank Foster, Drew Gress, John Hollenbeck, Ari Hoenig og Perico Sambeat. \n" +
                "\n" +
                "Agnar hefur verið kennari við Tónlistarskóla FÍH frá 2001. Hann hefur útskrifað fjölda nemenda og sá um að skrifa ritmíska-píanóhlutann í aðalnámskrá tónlistarskólanna fyrir Menntamálaráðuneytið.\n" +
                "\n" +
                "Auk þessa hefur Agnar á tónlistarferli sínum unnið til verðlauna svo sem „Outstanding Musicianship Award“ frá Berklee tónlistarháskólanum í Boston og komist í undanúrslit í Alþjóðlegu djass-píanókeppninni Martial Solal í París haustið 2002. Hann hefur útsett lög fyrir geislaplötur og söngleiki, starfað með Stórsveit Reykjavíkur í mörg ár, leikið á tónleikum hérlendis sem erlendis, verið píanóleikari og annast tónlistarstjórn í leikhúsi. Agnar sá meðal annars um tónlistina í Söngvaseið, Mary Poppins og Billy Elliot fyrir Borgarleikhúsið. Agnar starfar nú sem djasspíanisti en kennir jafnframt píanóleik við djassdeild Tónlistarskóla FÍH. Agnar var útnefndur bæjarlistamaður Garðabæjar 2010.", "JazzPíanó", "Agnar_Mar_magnusson_lowres.jpg"),null);
        teacherService.save(new Teacher("Andrés Þór Gunnlaugsson", "AÞG", "andresthor@internet.is", "699 6327", "Andrés Þór lauk burtfararprófi frá Tónlistarskóla FÍH 1999, BA gráðu í jazzgítarleik og kennslufræðum frá Konunglega tónlistarháskólanum í Haag í Hollandi 2004 og MA gráðu frá sama skóla árið 2006.  Andrés hóf að starfa sem hljóðfæraleikari 1994 á dansleikjum en síðari ár hefur hann verið virkur í íslensku tónlistarlífi með eigin jazzhljómsveitir auk þess að starfa sem hljóðfæraleikari í hljóðverum, tónleikauppfærslum og í leiksýningum.  Andrés hefur gefið út fjölmarga hljómdiska í eigin nafni sem og í samstarfi við aðra. Andrés hefur margoft verið tilnefndur til Íslensku tónlistarverðlaunanna og var útnefndur Bæjarlistamaður Hafnarfjarðar árið 2014.", "Rafgítar", "Andrer_Thor_Gunnlaugsson_lowres-200x300.jpg"), null);
        teacherService.save(new Teacher("Ásgeir Jón Ásgeirsson", "ÁJÁ", "asgeirja@simnet.is", "863 3943", "Ásgeir Ásgeirsson er einn fremsti og fjölhæfasti gítarleikari Íslands og  er jafnvígur á marga ólíka stíla svo sem popp, rokk,kántrý, jazz og Balkantónlist. Einnig er Ásgeir mjög eftirsóttur undirleikari en hann kemur reglulega fram með Páli Óskari Hjálmtýssyni. Ásgeir hefur leikið inná u.þ.b. eitt hundrað hljómplötur á yfir 50 frumsamin lög á skrá hjá STEF. Ásgeir hefur verið  alloft tilnefndur til íslensku tónlistarverðlaunana fyrir lagasmíðar sínar í popp/rokk, jazzflokki og opnum flokki. Ásgeir hefur á rúmlega tuttugu ára ferli sínum sem atvinnutónlistarmaður leikið með öllum fremstu tónlistarmönnum landsins, sem og allmörgum þekktum erlendum hljóðfæraleikurum s.s. Dave Weckl, Guthrie Govan, Chris Cheek, Seamus Blake, Ingrid Jensen, Borislav Zgurovski og Claudio Spieler og Hanan El frá Egyptalandi.\n" +
                "\n" +
                "Ásgeir útskrifaðist sem jazz einleikari frá Tónlistarskóla FÍH og nam framhaldsnám í  jazzgítarleik í Conservatorium van Amsterdam frá 1999 til 2001. Árið 2006 hóf Ásgeir að nema Balkantónlist en Ásgeir hefur farið fjölmargar námsferðir til Búlgaríu, Grikklands og Tyrklands  og lært á þjóðlagagítara þessara landa frá fremstu kennurum og tónlistarmönnum í þessum löndum og kemur reglulega fram á tónleikum og hljómplötum sem flytjandi á tamboura, bouzouki, saz baglama og oud. Ásgeir hefur gefið út tvær sólóplötur: Passing through 2006 og Trio 2016. Ásgeir hefur verið kennari við Tónlistarskóla FÍH frá árinu 2001. Ásgeir er bæjarlistamaður Kópavogs 2016", "Rafgítar", "Asgeir_Asgeirsson_lowres.jpg"), null);
        teacherService.save(new Teacher("Birgir Bragason", "BB", "bb@hive.is", "892 4378", "Birgir Bragason nam flautuleik við Tónlistarskólann í Reykjavík en sneri sér svo að bassaleik. Efitr stutta viðdvöl í Tónskóla Sigursveins fór hann í Tónlistarskóla FIH og lærði þar bæði á kontrabassa og rafbassa. Birgir hefur síðan verið mjög virkur í tónlistarlífi landsins undanfarna áratugi og tekið þátt í margs konar ólíkum tónlistarflutningi og leikið inn á tugi hljómdiska. Hann hefur unnir mikið í leikhúsum og tekið þátt í á þriðja tug leiksýninga í Þjóðleikhúsinu og hjá Leikfélagi Reykjavíkur. Má þar nefna Mary Poppins, Söngvaseið, Edith Piaf, Óliver og Túskildingsóperuna.\n" +
                "\n" +
                "Birgir hefur starfað sem tónlistarkennari frá árinu 1989. Við Tónlistarskólann í Keflavík 1989-95, við Tónlistarskóla Hafnarfjarðar frá 1994 til 2000 og frá árinu 2000 við Tónlistarskóla FÍH  þar sem kennslugreinar hans hafa verið rafbassi, kontrabassi, samspil og kennsla í kennaradeild.", "Rafbassi", "Birgir_Bragason_lowres.jpg"), null);
        teacherService.save(new Teacher("Einar Scheving", "ES", "escheving@gmail.com", "897 9506", "Einar Scheving hefur verið eftirsóttur trommu- og slagverksleikari í djass-, popp- og klassískri tónlist frá unglingsaldri og hefur hann leikið inn á vel yfir 100 geisladiska. Einar útskrifaðist frá Tónlistarskóla FÍH, stundaði framhaldsnám við University of Miami og lauk þaðan MA-prófi árið 2002. Hann varð kennari við skólann að námi loknu, en samhliða því starfaði hann sem tónlistarmaður þar vestra.\n" +
                "\n" +
                "Einar hefur lagt aukna áherslu á tónsmíðar í seinni tíð, og hefur hann þrisvar hlotið Íslensku tónlistarverðlaunin, m.a. fyrir plöturnar Cycles (2007) og Land míns föður (2011). Báðar þessar plötur hlutu mikið lof gagnrýnenda jafnt hérlendis sem erlendis. Nýjasta plata Einars, Intervals, kom út í október 2015 og hefur þegar vakið mikla athygli, auk þess að hljóta tilnefningu til Íslensku tónlistarverðlaunanna 2015. Einar hefur kennt við Tónlistarskóla FÍH frá árinu 2006.", "Slagverk", "Einar_Scheving_minni.jpg"), null);
        teacherService.save(new Teacher("Eiríkur Örn Pálsson", "EÖP", "heirikur@talnet.is", "562 2434 / 698 2034", "Eiríkur stundaði nám við Tónlistarskólann í Reykjavík til 1982. Hann lauk B.M. próf frá Berklee College of Music 1985. Eftir það var hann eitt ár í einkanámi, en hélt síðan til framhaldsnáms við California Institude of the Arts í Los Angeles. Hann lauk M.F.A. prófi þaðan 1988. Þá hefur Eiríkur stundað tónsmíðanám hjá Atla H. Sveinssyni, John Bavicci og Stephan Mosho.\n" +
                "\n" +
                "Eiríkur Örn er fastráðinn trompetleikari í Sinfóníuhljómsveit Íslands frá 1996. Hann hefur verið meðlimur Caput hópsins frá upphafi og hefur leikið á fjölmörgum diskum hópsins auk þess að hafa farið í tónleikaferðir með hópnum til fjölmargra landa.\n" +
                "\n" +
                "Hann leikur reglulega með Kammersveit Reykjavíkur og hefur hljóðritað fjölmarga diska með sveitinni, m.a.  trompetkonserta eftir J. F. Fasch og Leopold Mozart og farið í tónleikaferðir m.a. til Kína, Japans og Rússlands.\n" +
                "\n" +
                "Hann frumflutti trompetkonsert eftir Jónas Tómasson með Sinfóníuhljómsveit áhugamanna. Á hljómdiskinum „Trompetaria“ leikur hann ásamt öðrum trompetleikara og organista tónlist fyrir trompet og orgel.\n" +
                "\n" +
                "Trompetleik Eiríks Arnar hefur mátt heyra á leiksýningum Þjóðleikhússins og Borgarleikhússins og í Íslensku óperunni.  Hann lék einnig um tíma með Stórsveit Reykjavíkur. Auk þess sem hann hefur leikið í hljóðverum fyrir kvikmyndir, sjónvarp og hjómdiskaútgáfur ýmiskonar.\n" +
                "\n" +
                "Hann kennir við Tónlistarskólann í Reykjavík, Tónlistarskóla FÍH og Listaháskóla Íslands.", "Trompet", "Eirikur_Orn_Palsson.jpg"), null);
        teacherService.save(new Teacher("Erik Qvick", "EQ", "erikqvick@gmail.com", "690 2719", "Nám við Musikhögskolan Ingesund, 1994-2000. Lauk Musiklärarutbildning, Master Of Fine Arts Music Education og Påbyggnadsutbildning Slagverk/Ensemble. MS í Mannauðsstjórnun frá viðskiptafræðideild H.Í. 2015. Fastráðinn kennari við Tónlistarskóla FIH frá 2000. Hefur einnig kennt við Tónlistarskóla Akraness. Skólalúðrasveit Akraness. Stundakennsla við Listaháskóla Íslands.", "Slagverk", "Erik_Qvick_minni.jpg"), null);
        teacherService.save(new Teacher("Guðni Kjartan Franzson", "GKF", "gudni@toney.is", "862 4913", "Gudni lauk einleikaraprófi á klarínettu og prófi frá tónfræðadeild Tónlistarskólans í Reykjavík árið 1984 meðal kennara þar voru; Einar Jóhannesson, Atli H. Sveinsson og Páll Pampichler. Hann fór síðan til Hollands og stundaði framhaldsnám í klarínettuleik hjá George Pieterson, Walter Boeykens og Harry Sparnaay. Guðni hlaut m.a. styrki frá Hollenska menntamálaráðuneytinu og hinum danska Léonie Sonning sjóði. Guðni hefur komið fram sem einleikari í mörgum löndum Evrópu, Brazilíu, Canada, Japan og í fyrrum Sovétríkjum, hljóðritað fjölda geisladiska með nýrri og klassískri tónlist jafnframt því að leika og hljóðrita þjóðlega tónlist s.s. með Rússíbönum.\n" +
                "\n" +
                "Guðni var einn af stofnendum CAPUT árið 1987 en hópurinn er meðal eftirtektarverðustu flytjenda nýrrar tónlistar í Evrópu, hefur hljóðritað á þriðja tug geisladiska og leikið á mörgum virtustu tónlistarhátíðum veraldar.  Guðni stýrir gjarnan CAPUT á tónleikum og við hljóðritun en stjórnar líka stundum Sinfóníuhljómsveit Íslands og Evrópskum hljómsveitum og kammerhópum.\n" +
                "\n" +
                "Samhliða hljóðfæraleiknum vinnur Guðni sem tónsmiður.  Vorið 2009 hlaut hann Grímuverðlaunin fyrir tónlistina við leikverkið Steinar í Djúpinu, í uppsetningu Lab Loka og Hafnarfjarðarleikhússins.  Tóney er skapandi vettvangur fyrir tónlist og hreyfingu sem Guðni stofnaði árið 2007 og starfrækir með hópi valinkunnra listamanna, auk þess að kenna við Tónlistarskóla FÍH og Listaháskóla Íslands.", "Fræðigreinar, Klarínetta", "Gudni_franzson_minni.jpg"),null);
        teacherService.save(new Teacher("Hildur Guðný", "HG", "hildurgudnyth@gmail.com", "698 3263", "Hildur Guðný nam klassískan píanóleik og jazzsöng við Tónlistarskóla FÍH.  Hún lauk tónmenntakennaraprófi frá Tónlistarskólanum í Reykjavík vorið 1998 og um haustið sama ár hóf hún störf sem tónfræðakennari við Tónlistarskóla FÍH. Hildur Guðný kennir í dag kennslufræði við kennaradeild skólans og heldur þar utan um æfingakennslu ásamt því að kenna tónheyrn og hrynþjálfun í grunndeild.  Hrynþjálfunarfræðin lærði hún í Rytmisk Musikkonservatorium (RMC) í Kaupmannahöfn.\n" +
                "\n" +
                "Hildur Guðný vinnur mjög mikið í verkefnum tengdum sköpunarþætti tónlistarkennslu og er einn af frumkvöðlum þess háttar kennslu á Íslandi.  Hún starfar sjálfstætt við skapandi tónlistarkennslu á öllum skólastigum í almennum skólum jafnt sem í tónlistarskólum.  Hildur vinnur einnig með hópefli í skólum og fyrirtækjum þar sem hún hristir saman hópa af öllum aldri, stærðum og gerðum með hrynþjálfun, trommuslætti, söng og dansi.", "Fræðigreinar", "Hildur_Gudny_Thorhallsdottir.jpg"), null);
        teacherService.save(new Teacher("Hilmar Jensson", "HJ", "hilmarjensson@simnet.is", "862 3999", "Hilmar Jensson hóf að leika á gítar 6 ára gamall. Hann útskrifaðist frá Tónlistarskóla FíH 1987 og frá Berklee College of Music 1991. Einnig tók hann fjölda einkatíma utan skólans m.a. hjá Mick Goodrick, Joe Lovano, Hal Crook og Jerry Bergonzy. Hilmar hefur verið atkvæðamikill að námi loknu og hefur leikið í yfir 30 löndum og á um u.þ.b 70 geisladiskum. Hann hefur leikið með miklum fjölda þekktra tónlistarmanna og á mörgum helstu jazzhátíðum heims.\n" +
                "\n" +
                "Hann er einn stofnenda Tilraunaeldhússins og útgáfuarmi þess “Kitchen Motors”. Hilmar hefur verið kennari við FÍH frá 1992 og hefur einnig kennt við Listaháskóla Íslands. Hann hefur haldið fjölda fyrirlestra og/eða verið gestakennari í 30 háskólum í Evrópu, Bandaríkjunum og Kanada.", "Rafgítar-snarstefjun","Hilmar_Jensson_lowres.jpg"), null);
        teacherService.save(new Teacher("Jóhanna Guðríður Linnet", "JGL", "linnet@internet.is", "690 8505", "Jóhanna stundaði söngnám hjá Sieglinde Kahman við Tónlistarskólann í Reykjavík og síðar við Nýja tónlistarskólann þaðan sem hún lauk einsöngvaraprófi undir handleiðslu Sigurðar Demetz Franzsonar og Ragnars Björnssonar.\n" +
                "\n" +
                "Jóhanna stundaði framhaldsnám í Hollandi þar sem Dixie Ross Neill og William Neill voru hennar aðalkennarar. Í Hollandi tók Jóhanna þátt í uppfærslum Operastudios hollensku Operunnar, m.a. á La bohemé og einnig söng hún með kór hollensku Óperunnar.\n" +
                "\n" +
                "Eftir heimkomuna hefur Jóhanna sungið við hinar ýmsu uppfærslur, m.a. í Þjóðleikhúsinu, Borgarleikhúsinu, með Óperusmiðjunni og íslensku Óperunni. Þá hefur hún flutt samtímatónlist, tekið þátt í frumflutningi á óperu eftir Sigurð Sævarsson, sungið einsöng með fjölda kóra, með stórsveit, sinfóníuhljómsveit, hljómsveit Hauks Morthens, Grétars Örvarssonar og Magnúsar Kjartanssonar. Jóhanna lauk diplómanáni í CVT árið 2008 og hefur stundað nám í íslenskum fræðum við Háskóla Íslands. Jóhanna hefur starfað sem söngkennari við Tónlistarskóla FÍH frá árinu 1988.", "Jazz-söngur", "Johanna_Linnet_lowres.jpg"), null);
        teacherService.save(new Teacher("Jónatan Garðarsson", "JG", "jonatang@simnet.is", "897 1788", "", "Fræðigreinar" , "jonatan_gardarsson_lowres-200x300.jpg"), null);
        teacherService.save(new Teacher("Kristinn Evertsson", "KE", "kristinn@fih.is", "849 3283", "Kristinn Evertsson (f. 1984) lauk B.A. í tónsmíðum frá Listaháskóla Íslands árið 2010. Áður hafði hann lokið Diploma frá School of Audio Engineering (SAE) í Amsterdam árið 2007. Kristinn er meðlimur í hljómsveitunum Valdimar og Tilbury. Hann hefur komið að fjölda tónlistarverkefna á ferli sínum sem hljóð- og tónlistarmaður. Einnig hefur hann stundað eigin tónsmíðar, bæði fyrir leikhús, dansverk, myndmiðla o.fl.", "Tæknimaður", "Kristinn-Evertsson_minni.jpg"), null);
        teacherService.save(new Teacher("Kristján Hrannar Pálsson", "KHP", "ugluspegill@gmail.com", "", "Kristján Hrannar Pálsson er fæddur í Reykjavík 1987. Hann nam klassískan píanóleik hjá Ágústu Hauksdóttur og seinna jazzpíanó hjá Þóri Baldurssyni og Agnari Má Magnússyni í FÍH. Vorið 2018 útskrifaðist hann með kirkjuorganistapróf frá Tónskóla Þjóðkirkjunnar.\n" +
                "Kristján hefur komið víða við sem laga og textahöfundur, hljóðfæraleikari, útsetjari og pródúser. Hann var einn af stofnmeðlimum 1860 þar sem hann samdi lög og texta, lék á píanó, kontrabassa, harmonikku og gítar. Árið 2013 gaf hann út sólóplötuna Anno 2013 þar sem hann þreytti frumraun sína sem raftónlistarmaður og fékk sú plata glimrandi dóma í Morgunblaðinu. Þá lék hann undir og söng í endurkomu 3 á palli með Eddu Þórarinsdóttur. Árið 2016 gaf hann út spunaplötuna Arctic Take One, sem er konseptverk um loftslagsbreytingar, og hélt jafnframt erindi um verkið á Arctic Circle ráðstefnunni í Hörpu. 2019 hlaut hann styrk frá tónmenntasjóði kirkjunnar til að ljúka orgelverkinu +2,0°C um hlýnun jarðar.\n" +
                "Haustið 2018 tók Kristján við sem organisti og tónlistarstjóri Óháða safnaðarins. Þar stjórnar hann jafnframt Óháða kórnum sem hann stofnaði fyrr um árið.", "Píanó", "Prófíll-nýr.jpg"), null);
        teacherService.save(new Teacher("Leifur Gunnarsson", "LG", "leifurgunnarsson@gmail.com", "868 9048", "Leifur Gunnarsson lauk burtfaraprófi frá tónlistarskóla FÍH vorið 2009 og hafði þá á námsárunum látið nokkuð til sín taka í tónlistarlífi á íslandi, þá aðallega á djass senunni en einnig tekið þátt í kontarbassa grúppum ýmissa strengjasveita og sinfóníuhljómsveita. Ári eftir útskrift frá FÍH var ferðinni heitið til Kaupmannahafnar til frekara náms og útskrifaðist hann frá Ritmíska konservatoríinu í Kaupmannahöfn vorið 2013 með Bmus gráðu í kontrabassaleik. Kennarar hans í Kaupmannahöfn voru þeir Klavs Hovman, Jens Skou og Jesper Bodilsen á jazz brautinni og Andreas Bentzen sem þjálfaði klassíska spilið.\n" +
                "\n" +
                "Haustið 2015 gaf Leifur út sína fyrstu sólóplötu sem hefur fengið prýðis viðtökur. Hann er listrænn stjórnandi tónleikaraðarinnar Jazz í hádeginu sem unnin er í samvinnu við Borgarbókasafn og situr í framkvæmdastjórn Jazzhátíðar Reykjavíkur.\n" +
                "\n" +
                "Auk þess að hafa verið virkur í hljómsveitastarfi hefur Leifur skrifað og flutt eigin tónlist innan lands sem utan, gert margmiðlunarverkefni tengd tónlist og spuna, unnið að hljóðupptökum og nótnabókaútgáfu.", "Samspil", "Leifur_Gunnarsson_minni.jpg"), null);
        teacherService.save(new Teacher("Margrét Eir", "ME", "margret@margreteir.com", "", "Margrét Eir hefur starfað sem söngkona og leikkona á Íslandi í yfir tuttugu ár. Á þessum árum hef hún starfað með helstu tónlistarmönnum landsins, komið fram á tónleikum og sungið inn á fjölmargar plötur sem sólósöngvari og bakrödd. Margrét hefur meðal annars sungið með Sinfóníuhljómsveit Ísland (á War of the Worlds og Heiðurstónleikum Gunnars Þórðarsonar og Jólatónleikum) Margrét var einnig ein af aðalsöngkonum Frostrósa. Hún hefur síðan haldið sína jólatónleika síðustu 3 ár í Reykjavík og víðsvegar um landið. Hún syngur reglulega á tónleikum í Salnum og í Hörpu. Síðustu verkefni voru Trio í Salnum ásamt Guðrún Gunnar og Regínu Ósk, Heiðurstónleikar Villa Vill í Hörpu (Rigg) og Phantom of the Opera í Hörpu (TMB)\n" +
                "\n" +
                "Margrét útskrifaðist frá Raddskóla Kristin Linklater árið 2007 í New York og stofnaði söngskóla árið 2009 undir nafninu MEiriskóli. Hún lauk námi í leiklist við Emerson College í Boston 1998.  Margrét hefur leikstýrt í unglingaleikhúsum, götuleihúsum, framhaldsskólum og áhugaleikhúsum.Frá árinu 2000 hefur Margrét verið fastráðin hjá Sýrlandi hljóðsetningu við talsetningar á teiknimyndum fyrir sjónvarp og bíómyndir. Margrét hefur starfað talsvert í leikhúsi. Meðal sýninga sem hún hefur leikið og sungið í eru Hárið í Gamla bíói, Oliver hjá LA og RENT, Með fullri reisn og Glanni Glæpur í Latabæ og Madame Thernardier í Veslingunum hjá Þjóðleikhúsinu. Margrét var í hlutverki Mrs Andrews og Bird lady í Mary Poppins sem var sett upp í Borgarleikhúsinu 2013.\n" +
                "\n" +
                "Árið 2000 gaf Margrét út sína fyrstu sólóplötu og tvær aðrar fylgdu í kjölfarið. Fjórða plata hennar MoR Duran var dúettaverkefni með Róbert Þórhallssyni bassaleikara . Platan fékk mikla athygli í Bandaríkjunum, og var spiluð á yfir 250 útvarpsstöðvum í Bandaríkjunum og Kanada. Árið 2007 hóf hún samstarf við hljómsveitinni Thin Jim og fyrst platan þeirra This is me kom út árið 2012 og árið 2014 kom út dúetta plata If I needed you í samstarfi við Pál Rósinkranz.", "Jazz-Söngur", "Margret_Eir.jpg"), null);
        teacherService.save(new Teacher("María Magnúsdóttir", "MM", "mariamagnus@gmail.com", "", "María Magnúsdóttir lauk gráðunni Master of Popular Music með láði árið 2016.  Þar lagði hún sérstaka stund á nám í tónsmíðum fyrir miðla, upptökutækni og hljóðvinnslu.  Hún stundaði Bachelor nám í jazzsöng við Royal Conservatoire of The Hague í Hollandi sem hún lauk vorið 2015 með láði og hlaut útskriftarverðlaunin Fock Medaille.  Í Hollandi lagði hún einnig stund á tónstmíðar og sönglagasmíðar sem aukafag við tónlistarskólann Codarts í Rotterdam.  María lauk áður burtfararprófi og kennaraprófi frá Tónlistarskóla FÍH vorið 2008.\n" +
                "\n" +
                "María er starfandi tónlistarmaður og hefur komið víða við í tónlist.   Hún hefur starfað sem söngkona og flutt eigið efni og annarra síðan 2006,  Utan þess að koma reglulega fram sem jazzsöngkona semur hún, hljóðhannar og útsetur orchestral-pop tónlist undir listamannsheitinu MIMRA.  María var kjörin bæjarlistarmaður Garðabæjar árið 2018.  María hefur samið og útsett tónlist fyrir stuttmyndir og kóra og starfar nu sem kennari við Menntaskóla í Tónlist og Tónlistarskóla FÍH þar sem hún kennir söng og lagasmíðar.", "Jazz-söngur", "MaríaMagnus_mynd-600x693.jpg"), null);
        teacherService.save(new Teacher("Matthías M. D. Hemstock", "MMDH", "matthias@hemstock.net", "864 7084", "Menntun við Tónlistarskóla FÍH á árunum 1984-1988. Berklee College of Music 1989-1991, auk námskeiða, meðal annars hjá Dave Weckl.\n" +
                "\n" +
                "Matthías hefur starfað við tónlistarflutning frá 1984. Verkefnin hafa spannað vítt svið, allt frá rokki og popptónlist til klassískrar tónlistar. Jazz- og spunatónlist hefur verið rauður þráður síðan á árunum í TFÍH og Berklee. Hann hefur meðal annars leikið með Todmobil, Unun, Sinfóníuhljómsveit Íslands, í leikritum og söngleikjum í Borgarleikhúsinu og Þjóðleikhúsinu (Yerma, Vesalingarnir, Kabarett, Evita ofl.), Tómasi R. Einarssyni, Jóel Pálssyni, Hilmari Jenssyni, Skúla Sverrissyni, Óskari Guðjónssyni, Ómari Guðjónssyni, Tena Palmer, Rússíbönum, Tatu Kantomaa, Kammersveit Reykjavíkur, Richard Andersson, Caput hópnum og fjölmörgum öðrum, innanlands sem utan. Raftónlist hefur einnig skipað sinn sess, meðal annars í samstarfi við Jóhann Jóhannsson á árunum 2000 - 2012.\n" +
                "\n" +
                "Matthías hefur kennt á trommusett og slagverk við Tónlistarskóla FÍH frá árinu 1991. Þar hefur hann ásamt samkennurum mótað kennslu á trommusett. Sú vinna gangaðist síðan við gerð Aðalnámskrár tónlistarskóla en Matthías sá um þann hluta sem snýr að trommusettinu í rytmíska huta námskrárinnar. Hann stóð fyrir útgáfu bókarinnar Hringir innan hringja eftir Pétur Östlund sem er einstök kennslubók í tækni á trommusett.", "Slagverk", "Matthias_Hemstock_minni.jpg"), null);
        teacherService.save(new Teacher("Ólafur Hólm Einarsson", "ÓHE", "oliholm@hotmail.com", "699 6048", "Ólafur hefur spilað á trommur frá 10 ára aldri og er einna þekktastur fyrir trommuleik sinn með Nýdönsk. Ólafur starfar einnig með Todmobile og Dúndurfréttum. Ólafur hefur spilað með fjölmörgum af helstu tónlistarmönnum Íslands og leikið á yfir 100 hljómplötum. Auk þessa hefur Ólafur margoft leikið með Sinfóníuhljómsveit Íslands.\n" +
                "\n" +
                "Einnig hefur Ólafur unnið mikið í leikhúsunum og eru helstu verkefnin Gauragangur, Rocky Horror Picture Show, Evíta, Rent, Grease, Dýrin í Hálsaskógi, Túskildingsóperan, Hárið, Footloose og Vesalingarnir. Ólafur spilar um þessar mundir í Mamma Mia í Borgarleikhúsinu.\n" +
                "\n" +
                "Ólafur tók burtfararpróf frá Tónlistarskóla FÍH árið 1992 og hefur kennt þar síðan 1996. Árið 2005 kom út kennslubók í trommuleik eftir Ólaf.", "Slagverk", "Olafur_Holm_lowres.jpg"), null);
        teacherService.save(new Teacher("Ólafur Jónsson", "ÓJ", "olafurjonsson@simnet.is", "868 6673", "Ólafur Jónsson saxófónleikari er fæddur í Reykjavík 1967, hóf tónlistarnám 9 ára gamall í Tónskóla Sigursveins D. Kristinssonar þar sem hann lærði á klarinett, fyrst hjá Einari Jóhannessyni og síðar hjá Guðna Franzsyni.\n" +
                "\n" +
                "Árið 1983 hóf hann nám á saxófón við Tónlistarskóla FÍH, kennar hans voru Stefán S. Stefánsson, Vilhjálmur Guðjónsson, Gunnar Hrafnsson o.f.l.\n" +
                "\n" +
                "Frá árinu 1989 stundaði hann framhaldsnám í Boston við Berklee College of Music og útskrifaðist þaðan með BM gráðu vorið 1992. Aðalkennarar hans voru Bill Pierce, Joe Viola og George Garzone, jafnframt sótti hann einkatíma hjá Jerry Bergonzi og Hal Crook. Veturinn 1992-1993 einkanám hjá George Coleman og Joe Lovano í New York.\n" +
                "\n" +
                "Ólafur hefur stundað kennslu frá árinu 1993 við Tónlistarskóla FÍH og Tónskóla Sigursveins á saxófón ásamt bóklegum greinum og samspili. Hann hefur haldið fjölda tónleika með eigin hljómsveitum og í samstarfi við aðra bæði frumsamda tónlist og tónlist annara, bæði innanlands og utan. Einnig leikið inn á fjölmargar hljómplötur, m.a. með eigin tónlit, starfað í leikhúsum, leikið með Sinfóníuhljómsveit Íslands og verið fastur meðlimur í Stórsveit Reykjavíkur frá árinu 1994. Ólafur hefur hlotið starfslaun listamanna í nokkur skipti.", "Fræðigreinar, Saxófónn,", "Olafur_Jonsson.jpg"), null);
        teacherService.save(new Teacher("Róbert Þórhallsson", "RÞ", "robert@fih.is", "898 0394", "Byrjaði 11 ára í formlegu tónlistarnámi, lærði á trompet í 3 ár í Tónlistarskóla Húsavíkur og lék auk þess á trommur með samspilum þar. Byrjaði að spila á bassa 15 ára og hóf nám í Tónlistarskóla FÍH 2 árum seinna. Stúdent frá Menntaskólanum við Sund 1991. Stundaði nám við Tónlistarskóla FÍH Frá 1988 og lauk burtfararprófi þaðan vorið 1997.Hóf nám í Conservatorium van Amsterdam í Hollandi haustið 1998. Kláraði kennarapróf á 3 árum og mastersgráðu á 2 árum í bassaleik og útskrifaðist cum laude, fyrstur nemanda í Jazz- og hryndeild, vorið 2003. Sótti einkatíma í tónsmíðum og útsetningum í Amsterdam. Sótti einnig fjölda einkatíma hjá erlendum bassakennurum eins og : Victor Bailey, Jimmy Haslip, Gary Willis, Avishai Cohen auk annarra. Spilaði Masterclassa meðal annars með Mike Stern og Seamus Blake.\n" +
                "\n" +
                "Hefur kennt við Tónlistarskól FÍH frá 2003, tónvinnsluskóla Reykjavik Music Production 2004-2005, Tónlistarskóla Reykjanesbæjar frá 2009. Vann að námskrá fyrir rafbassa og kontrabassa í Námskrá Rythmískrar tónlistar fyrir Menntamálaráðuneytið og er að vinna að drögum um reglugerð fyrir Mennta- og menningarmálaráðuneytið.\n" +
                "\n" +
                "Hefur verið útgefandi og meðútgefandi á plötum, auk þess verið session-spilari á um 100 plötur og geisladiska. Hefur leikið á fjölda stórtónleika; Frostrósir, Jólagestir Björgvins, Villa Vill minningartónleikum, Páll Óskar í Háskólabíói og Hörpu. Hef verið í fjölda stórverkefna fyrir Rigg viðburði: Bee Gees, Bat out of Hell auk annarra tónleika. Spilað í söngleikjunum Superstar, Stonefree og Billy Elliot. Spilað með Sinfoníuhljómsveit Íslands Sgt. Peppers. Hefur leikið með fjölda erlendra tónlistarmanna, t.d. Grana Louise, Zora Young, Deitra Farr, Michael Burks, John Grant, Pinetop Perkins, John Primer, Vasti Jackson og Marquise Knox, Robben Ford, Guthrie Govan, Karen Lovely, John Del Toro Richardson, svo einhverjir séu nefndir.", "Rafbassi/Skólastjórn TFÍH", "Robert_Thorhallsson.jpg"), null);
        teacherService.save(new Teacher("Snorri Sigurðarsson", "SS", "snorrisigurdar@gmail.com", "898 3782", "Burtfararpróf í jazztrompet frá Tónlistarskóla FÍH 1998, rytmískt kennarapróf frá sama skóla 1999 og BA í jazztrompetleik frá Conservatorium Van Amsterdam 2002. Samspilskennsla og trompetkennsla á framhaldsstigi við Tónlistarskóla FÍH frá 2010. Stjórnandi Stórsveitar skólans frá 2014. Kennari við Skólahljómsveit Kópavogs frá 2003. Prófdómari hjá Prófanefnd tónlistarskóla frá 2010.\n" +
                "\n" +
                "Trompetleikari og sólisti í Stórsveit Reykjavíkur undir stjórn margra þekktra gestastjórnenda frá 1996. Hefur einnig samið og útsett fyrir hljómsveitina. Einnig trompetleikari í Stórsveit Samúels Jóns Samúelssonar frá 2005. Auk þess að vera virkur í djasslífinu hefur Snorri verið eftirsóttur spilari í poppgeiranum um árabil og hefur leikið með mörgum þekktustu listamönnum þjóðarinnar. Meðlimur í hljómsveitunum Jólakettir (1999), Páll Óskar og Casino (1998-1999), Sælgætisgerðin (1995-97). Jónas Sigurðsson og Ritvélar framtíðarinnar (2010-).\n" +
                "\n" +
                "Snorri hefur gert eina sólóplötu “Vellir” (2014). Hann hefur leikið inn á ótal hljómplötur fyrir ólíka listamenn en þar má nefna: Sigurrós: Ágætis byrjun (1999), Takk (2005), Inní mér syngur vitleysingur (2008), Ragnheiður Gröndal: Vetrarljóð (2004), Jónas Sigurðsson: Allt er eitthvað (2010).", "Big Band, Samspil", "Snorri_Sigurdarson_minni.jpg"), null);
        teacherService.save(new Teacher("Stefán S. Stefánsson", "SSS", "stefstef@centrum.is", "698 1597", "Fæddur 1957. Saxófónleikari, tónskáld og útsetjari. Stúdentspróf úr eðlisfræðideild Menntaskólans við Tjörnina 1977. Nám í heimspeki við Háskóla Íslands 1979-1980. Þverflautunám við Tónskóla Sigursveins og Tónlistarskólann í Reykjavík. Einkatímar á saxófón hjá Gunnari Ormslev. Lauk Bachelor of Music.(BM) prófi frá Berklee College of Music 1980-1983 í Boston og sótti nám í jazztónsmíðum við sama skóla 1988. Nám við tölvunarfræðibraut Iðnskólans í Reykjavík. Nám við tölvunarfræðideild Háskóla Reykjavíkur 2002 og 2005 í kerfisfræði ásamt fjölda námskeiða í tölvutækni.\n" +
                "\n" +
                "Stefán hefur leikið með ýmsum dans- og jazzhljómsveitum og starfað sem hljóðfæraleikari í leikhúsum, Sinfóníuhljómsveit Íslands og með Stórsveit Reykjavíkur sem stjórnandi og hljóðfæraleikari. Hann hefur útsett og samið mikið af tónlist m.a. fyrir Stórsveit danska útvarpsins, Íslensku hljómsveitina og Stórsveit RÚV. Þá hefur hann gert kvikmyndatónlist og tónlist og texta á hljómplötur m.a. fyrir hljómsveitirnar Ljósin í bænum, Gamma, Mezzoforte, Tamlasveitina og Björn Thoroddsen.\n" +
                "\n" +
                "Stefán er nú starfandi kennari og skólastjóri við Tónlistarskóla Árbæjar ásamt hljóðfæraleik með ýmsum hljómsveitum. Stefán hlaut Íslensku tónlistarverðlaunin árið 2015 sem tónhöfundur ársins og fyrir plötu ársins í jazzflokki: Íslendingur í Alhambrahöll.", "Fræðigreinar", "StefanSStefansson_lowres.jpg"), null);
        teacherService.save(new Teacher("Svanur Vilbergsson", "SV", "svilbergsson@gmail.com", "857 3901", "Svanur hóf gítarnám sitt  hjá Torvald Gjerde, Garðari Harðarssyni og Charles Ross við Tónlistarskóla Stöðvarfjarðar og Tónlistarskólann á Egilsstöðum. Sautján ára fór hann til Englands til náms við King Edwards VI menntaskólann í Totnes þar sem gítarkennari hans var Colin Spencer og útskrifaðist þaðan af tónlistar- og líffræðibraut árið 2001. Þaðan hélt hann til Spánar og sótti þar einkatíma hjá Arnaldi Arnarssyni við Escola Luther. Árið 2002 hóf Svanur nám hjá ítalska gítarleikaranum Carlo Marchione við Tónlistarháskólann í Maastricht og lauk þaðan B.Mus. gráðu vorið 2006. Sama ár hóf hann mastersnám hjá Enno Voorhorst við Konunglega Tónlistarháskólann í Haag sem hann lauk vorið 2008. Þá hefur hann einnig sótt tíma hjá Sonju Prunnbauer í Freiburg.\n" +
                "\n" +
                "Svanur hefur haldið einleikstónleika víða um heim, m.a. í Bandaríkjunum, Hollandi, Spáni, Englandi, Belgíu og Írlandi. Honum er reglulega boðið að spila í Casa Eulalia tónleikaröðinni á Mallorca og á nútímatónlistarhátíðinni Klanken Festival í Maastricht. Á meðal nýlegra verkefna  hafa verið tónleikar á Inishowen International Guitar Festival á Írlandi, Semersooq gítarhátíðinni á Grænlandi og Sommer melbu hátíðinni í Noregi. Hann hefur komið fram í sjónvarpi í Bandaríkjunum og á Spáni og var valinn fyrir Íslands hönd til þátttöku í norsk-íslenska menningarverkefninu Golfstraumurinn. Spænska tónskáldið Mateu Malondra Flaquer hefur tileinkað honum verk fyrir sóló gítar og  í febrúar 2014 frumflutti hann í Hörpu, ásamt Kammersveit Reykjavíkur, gítarkonsertinn Halcyon Days  sem saminn var af tónskáldinu Oliver Kentish og tileinkaður Svani. Árið 2011 kom út fyrsti einleiksdiskur Svans sem kallast Four Works og hefur honum verið einkar vel tekið. Svanur er listrænn stjórnandi tónleikaraðarinnar „Lofað öllu fögru“ sem fram fer í Þjóðmenningarhúsinu og alþjóðlegu gítarhátíðarinnar Midnight Sun Guitar Festival.\n" +
                "\n" +
                "Svanur kennir klassískan gítarleik við Tónlistarskóla Hafnarfjarðar, Tónlistarskóla FÍH, Tónlistarskólann í Reykjavík og Listaháskóla Íslands.", "Gítar", "Svanur_Vilbergsson2-300x200.jpg"), null);
        teacherService.save(new Teacher("Vernharður Linnet", "VL", "linnet@simnet.is", "", "Vernharður Linnet lauk kennaraprófi 1968. Kenndi við Grunnskóla Þorlákshafnar til 1980, utan eins vetrar er hann nam við Danmarks Lærerhøjskole í Kaupmannahöfn. Í Þorlákshöfn sinnti hann m.a. tónlistarkennslu við skólann. Hann stundaði nám við Tónlistarskóla Reykjavíkur árin 1959-1961, eftir það nam hann saxófónleik hjá Andrési Ingólfssyni.  Hann kenndi við Breiðholtsskóla frá 1980 til 2004, utan fimm ár er hann starfaði sem fastráðinn dagskrárgerðarmaður við Ríkisútvarpið. Frá 2004 hefur hann eingöngu starfað við djasstónlist, sem gagnrýnandi, þáttagerðarmaður og kennari.\n" +
                "\n" +
                "Vernharður hefur skrifað um djass í ýmis tímarit og dagblöð, íslensk sem erlend, frá 1964; var djassgagnrýnandi Helgarpóstsins 1979-1988 og Morgunblaðsins frá 1997 til þessa dags. Hann hefur rannsakað íslenska djasssögu lengi og vinnur nú að bók um efnið. Vernharður hefur verið formaður Jazzvakningar frá 1980 og var framkvæmdastjóri RúRek djasshátíðarinnar 1991-1996. Hann hefur unnið að gerð djassþátta fyrir Ríkisútvarpið frá 1980 m.a. um íslenska djasssögu. Vernharður hefur flutt fyrirlestra um djass hjá ýmsum félagasamtökum og skólum, allt frá grunnskólum til háskóla, og hefur kennt djasssögu við Tónlistarskóla FÍH frá 2014. Viðurkenningar fyrir kynningu á íslenskri djasstónlist: Gullheiðursmerki FÍH 2007 og Bjarkarlaufið 2009.", "JazzSaga", "Vernhardur_Linnet_minni.jpg"), null);
        teacherService.save(new Teacher("Vignir Þór Stefánsson", "VÞS", "vigstef@gmail.com", "861 1700", "Vignir Þór Stefánsson byrjaði átta ára gamall að læra á píanó í Tónlistarskóla Árnessýslu og var farinn að leika á dansleikjum átján ára gamall.\n" +
                "\n" +
                "Hann stundaði djasspíanónám í tónlistarskóla FÍH og lauk einnig tónmenntakennaraprófi frá Tónlistarskólanum í Reykjavík. Árið 1995 fluttist Vignir til Haag í Hollandi til að leggja stund á djasspíanó í Konunglega Tónlistarháskólanum í Haag. Þaðan lauk hann mastersprófi vorið 2001. Samhliða náminu lék Vignir með djasshljómsveitum af öllum stærðum og gerðum og á hljómborð í söngleikjum í atvinnuleikhúsum.\n" +
                "\n" +
                "Eftir að hafa flutt aftur heim til Íslands hefur Vignir komið víða við í íslensku tónlistarlífi, á tónleikum, sjónvarpsþáttum, hljómdiskum og í leikhúsum.\n" +
                "\n" +
                "Hann sinnir kennslu í tónlistarskóla FÍH og kennir þar djasspíanó og hljómborðsfræði kennaradeildar.", "Fræðigreinar, Jazzpíanó", "Vignir_Thor_Stefansson_lowres.jpg"), null);
        teacherService.save(new Teacher("Vilhjálmur H. Guðjónsson", "VHG", "villi@mmedia.is", "896 3783", "Ferilskrá: Stúdentspróf frá Menntaskólanum við Tjörnina. Tónmenntakennarapróf frá Tónlistarskólanum í Reykjavík. Blásarakennarapróf frá frá Tónlistarskólanum í Reykjavík. Jazzhljómfræði-, jazztónheyrnar- og jazzútsetningapróf frá Berklee College of Music í Boston.Vocational Diploma (próf í rock-, jazz-, fusion-, blues- og countrygítarleik) frá Guitar Institude of Technology í Kaliforníu. Stjórnaði Kór Menntaskólans við Sund í 3 vetur. Stjórnaði Kór Hagaskóla í 2 vetur. Raddæfði Karlakór Reykjavíkur 2 vetur. Stjórnaði Léttsveit Ríkisútvarpsins. Hef leikið á saxófón í nokkrum stórsveitum og ýmis hljóðfæri í danshljómsveitum í áratugi. Stjórnaði hljómsveit í undankeppnum Eurovision í sjónvarpssal. Stjórnaði hljómsveit í sjónvarpsþáttunum “Á tali með Hemma Gunn”. Stjórnaði hljómsveit í útvarpsþáttunum “úllen dúllen doff”. Var yfirkennari Jazzdeildar Tólistarskóla FÍH fyrstu 5 árin og kenndi þá jazz-hljómfræði, -tónheyrn, -útsetningar og –spuna ásamt hljóðfærakennslu á jazzgítar og saxófón og stjórnaði jazzsamspilum. Hef samið og útsett tónlist við kvikmyndir, leiksýningar, áramótaskaup, auglýsingar, sjónvarpsþætti o.fl. Hef leikið inn á, stjórnað og séð um upptökur fjölda hljómplatna.", "Rafgítar, Samspil", "Vilhjalmur_Gudjonsson2.jpg" ), null);
        teacherService.save(new Teacher("Þórður Árnason", "ÞÁ", "t-arnason@islandia.is", "891 6350", "Nám við Tónlistarskólann í Rvík 1976-8 og Berklee College of Music 1984-6. Gítar- og samspilskennari við Tónlistarskóla Hafnarfjarðar frá 1991 og Tónlistarskóla FÍH frá 1992.\n" +
                "\n" +
                "Þórður hefur stofnað og starfað með ýmsum hljómsveitum, t.d. Stuðmönnum, Hinum íslenzka Þursaflokki og Brunaliðinu. Hann hefur leikið inn á fjölmargar hljómplötur auk starfa í sjónvarpi og leikhúsi.", "Rafgítar", "Thordur_Arnason_minni.jpg"), null);

        courseService.save(new Course("Tónheyrnar- og hrynþjálfun a", "17:00-18:30", "hátíðarsalur","FÍH", "fimmtudagur", teacherService.findById(10L).getTeacher(), "Engar"));
        courseService.save(new Course("Tónfræði, hlustun og greining b", "17:00-18:30", "vestursalur", "FÍH", "fimmtudagur", teacherService.findById(9L).getTeacher(), "Engar"));
        courseService.save(new Course("Tónheyrnar- og hrynþjálfun b", "18:45-20:15", "hátíðarsalur", "FÍH", "Fimmtudagur", teacherService.findById(10L).getTeacher(), "Engar"));
        courseService.save(new Course("Tónfræði, hlustun og greining b", "18:45-20:15", "vestursalur", "FÍH", "fimmtudagur", teacherService.findById(9L).getTeacher(), "Engar" ));
        courseService.save(new Course("Hljóðtækni 1.1a", "13:00-14:30", "hljóðver/austursalur", "MÍT", "mánudagur", teacherService.findById(14L).getTeacher(), "Engar"));
        courseService.save(new Course("Hljóðtækni 1.1b", "14:30-16:00", "hljóðver/austursalur", "MÍT", "mánudagur", teacherService.findById(14L).getTeacher(), "Engar"));
        courseService.save(new Course("Jazzútsetningar 1.1", "16:00-17:30", "stofa 2", "MÍT", "mánudagur", teacherService.findById(24L).getTeacher(), "Rytmísk hljómfr.2.2"));
        courseService.save(new Course("Listin og lifibrauðið 1.1", "17:30-19:00", "stofa 2", "MÍT", "mánudagur", teacherService.findById(24L).getTeacher(), "Engar"));
        courseService.save(new Course("Rytm. hljómfr. 1.1a", "17:00-18:30", "vestursalur", "MÍT", "mánudagur", teacherService.findById(21L).getTeacher(), "Miðpróf í tónfræði"));
        newsStoryService.save(new NewsStory("Umsóknir fyrir næsta skólaár", "Opnað verður fyrir umsóknir vegna skólaársins 2019-2020.", "Fréttir", "umsokn.jpg"));
        newsStoryService.save(new NewsStory("Skólaárið 2019-2020", "Nú er skólaárið 2019-20 hafið og við bjóðum velkomna nýja nemendur og fögnum þeim sem fyrir voru. Það er góður hugur í kennurum skólans og  fyrirhugaðar eru spennandi breytingar og viðbætur, sem allar miða að auka ánægju og áhuga nemenda. Skólinn stefnir á að hafa fund þar sem farið verður yfir þessi mál seinna í haust.\n" +
                "\n" +
                "Undanfarin vika var undirlögð af samspilsprufum og það er verið að leggja lokahönd á að raða upp samspilshópunum. Eins og fyrri ár, reynum við að koma sem allra flestum að í samspil og verða niðurstöður kynntar innan skamms. \n" +
                "\n" +
                "Ég vill benda öllum nemendum á að vera vakandi yfir e-mailum frá skrifstofu og kennurum og veita skjót og skýr svör, svo skólaárið renni jafn ljúflega af stað sem fallegt lag.\n" +
                "\n", "Fréttir", ""));
        newsStoryService.save(new NewsStory("Ný vefsíða FÍH", "Ný vefsíða hefur verið gerð fyrir tónlistarskólann. Síðan inniheldur nú meðal annars algengar spurningar fyrir tilvonandi nemendur og frétta síðu og margt fleira. ", "Fréttir", "umsokn.jpg"));
        newsStoryService.getNewestStories();
        return "redirect:/";
    }




}
