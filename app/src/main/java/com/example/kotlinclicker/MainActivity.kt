package com.example.kotlinclicker
import android.content.Context
import android.media.MediaPlayer
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kotlinclicker.ui.theme.KotlinClickerTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.*
import kotlinx.coroutines.runBlocking
import org.w3c.dom.Text


class AudioPlayer(context: Context, audioResourceId: Int) {
    private val mediaPlayer = MediaPlayer.create(context, audioResourceId)

    fun play() {
        mediaPlayer.isLooping = true
        mediaPlayer.start()
    }

    fun stop() {
        mediaPlayer.stop()
        mediaPlayer.release()
    }
}
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            KotlinClickerTheme() {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    MineClicker()
                }
            }
        }
    }
}


@Composable
fun MusicaFundo(musica: Int,) {
    val context = LocalContext.current

    val audioPlayer = remember(musica) {
        AudioPlayer(context, audioResourceId = musica)
    }

    DisposableEffect(audioPlayer) {
        audioPlayer.play()

        onDispose {
            audioPlayer.stop()
        }
    }

}

@Composable
fun MineClicker() {

    var esmeraldas by remember {
        mutableStateOf(0)
    }

    var steve by remember {
        mutableStateOf(false)
    }

    var villager by remember {
        mutableStateOf(false)
    }

    var creeper by remember {
        mutableStateOf(false)
    }

    var secret by remember {
        mutableStateOf(false)
    }

    var musica by remember {
        mutableStateOf(R.raw.sweden)
    }

    var background by remember {
        mutableStateOf(R.drawable.cavehard)
    }

    LaunchedEffect(Unit) {
        while (true) {
            if (steve) {
                launch(Dispatchers.Default) {
                    esmeraldas += 1
                    delay(1000)
                }
            }
            if (villager) {
                launch(Dispatchers.Default) {
                    esmeraldas += 5
                    delay(1000)
                }
            }
            if (creeper) {
                launch(Dispatchers.Default) {
                    esmeraldas += 10
                    delay(1000)
                }
            }
            if (secret) {
                launch(Dispatchers.Default) {
                    esmeraldas += 1000
                    delay(1000)
                }
            }
            delay(1000) // delay de 1 segundo antes da próxima iteração
        }
    }

    var esmeraldaganha by remember {
        mutableStateOf(75)
    }
    var picareta by remember {
        mutableStateOf(R.drawable.woodpickaxe)
    }

    var pickupgrade by remember {
        mutableStateOf(R.drawable.stonepickaxe)
    }

    val click: () -> Unit = {
        esmeraldas += esmeraldaganha
    }

    val changepick: () -> Unit = {
        if (pickupgrade == R.drawable.stonepickaxe && esmeraldas >= 50) {
            esmeraldas -= 50
            picareta = R.drawable.stonepickaxe
            pickupgrade = R.drawable.ironpickaxe
            esmeraldaganha = 2
        }
        if (pickupgrade == R.drawable.ironpickaxe && esmeraldas >= 100) {
            esmeraldas -= 100
            picareta = R.drawable.ironpickaxe
            pickupgrade = R.drawable.diamondpickaxe
            esmeraldaganha = 3
        }
        if (pickupgrade == R.drawable.diamondpickaxe && esmeraldas >= 200) {
            esmeraldas -= 200
            picareta = R.drawable.diamondpickaxe
            pickupgrade = R.drawable.vermelho
            esmeraldaganha = 5
        }
    }

    val comprarsteve: () -> Unit = {
        if (steve == false && esmeraldas >= 75) {
            esmeraldas -= 75
            steve = true

        }
    }

    val comprarvillager: () -> Unit = {
        if (steve == true && villager == false && esmeraldas >=400 ) {
            esmeraldas -= 400
            villager = true

        }
    }

    val comprarcreeper: () -> Unit = {
        if (steve == true && villager == true && creeper == false && esmeraldas >= 800) {
            esmeraldas -= 800
            creeper = true

        }
    }

    val comprarsegredo: () -> Unit = {
        if (steve == true && villager == true && creeper == true && secret == false && esmeraldas >= 1000) {
            esmeraldas -= 1000
            musica = R.raw.gilgamesh
            background = R.drawable.hiitite
            secret = true
        }
    }

    TelaPrincipal(
            esmeraldas = esmeraldas,
            picareta = picareta,
            pickupgrade = pickupgrade,
            background = background,
            secret = secret,
            onStoneClick = click,
            onPickClick = changepick,
            onSteveClick = comprarsteve,
            onVillagerClick = comprarvillager,
            onCreeperClick = comprarcreeper,
            onFunnyClick = comprarsegredo

        )
        MusicaFundo(musica = musica)
}


@Composable
fun Esmeraldas(
    qtd: Int,
) {
    Row(
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
    ) {
        Image(
            painter = painterResource(R.drawable.__2_minecraft_emerald),
            contentDescription = "Esmeralda",
            modifier = Modifier
                .width(30.dp)
                .height(20.dp)
                .padding(top = 4.dp)
        )
        Text(
            text = "$qtd",
            color = Color.Green,
            modifier = Modifier.padding(start = 5.dp, top = 0.dp)
        )
    }
}


@Composable
fun TelaPrincipal
            (esmeraldas: Int,
             picareta: Int,
             pickupgrade: Int,
             background: Int,
             secret: Boolean,
             onStoneClick: () -> Unit,
             onPickClick: () -> Unit,
             onSteveClick: () -> Unit,
             onVillagerClick: () -> Unit,
             onCreeperClick: () -> Unit,
             onFunnyClick: () -> Unit,){



    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            modifier = Modifier.fillMaxSize(),
            painter = painterResource(id = background),
            contentDescription = "Background",
            contentScale = ContentScale.Crop,
        )
        Box(
            modifier = Modifier
                .fillMaxSize()
                .border(
                    if(secret != true){
                        BorderStroke(2.dp, Color(0xBCB6845D))
                    }else{
                        BorderStroke(2.dp, Color.Yellow)
                         },
                    shape = RoundedCornerShape(8.dp)
                ),
            contentAlignment = Alignment.TopCenter

        ) {
            if (secret == true) {
                Text(
                    text = "VOCÊ VENCEU!",
                    color = Color.Yellow,
                    modifier = Modifier.padding(start = 0.dp, top = 400.dp),
                    style = TextStyle(fontSize = 50.sp)
                )
                Column(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Esmeraldas(qtd = esmeraldas)
                }
            }

            if (secret != true) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Esmeraldas(qtd = esmeraldas)
                }

                Box(
                    modifier = Modifier
                        .width(45.dp)
                        .height(220.dp)
                        .padding(start = 12.dp, top = 12.dp)
                        .align(Alignment.TopStart)
                )
                IconButton(onClick = onPickClick, modifier = Modifier.align(Alignment.TopEnd),) {
                    Image(
                        painter = painterResource(id = pickupgrade),
                        contentDescription = "My Image 2",
                        modifier = Modifier
                            .align(Alignment.TopEnd)
                            .padding(top = 11.dp, end = 10.dp)
                            .width(70.dp)
                            .border(
                                BorderStroke(2.dp, Color(0xFF83816E)),
                                shape = RoundedCornerShape(8.dp)

                            )
                            .padding(15.dp)
                    )
                }
                IconButton(
                    onClick = onSteveClick,
                    modifier = Modifier
                        .padding(top = 10.dp, start = 10.dp)
                        .border(
                            BorderStroke(2.dp, Color(0xFF83816E)),
                            shape = RoundedCornerShape(8.dp)
                        )
                        .align(Alignment.TopStart),
                ) {
                    Image(
                        painter = painterResource(R.drawable.sbeve),
                        contentDescription = "My Image 2",
                        modifier = Modifier
                            .align(Alignment.TopStart)
                            .width(20.dp)
                    )
                }
                IconButton(
                    onClick = onVillagerClick,
                    modifier = Modifier
                        .padding(top = 70.dp, start = 10.dp)
                        .border(
                            BorderStroke(2.dp, Color(0xFF83816E)),
                            shape = RoundedCornerShape(8.dp)
                        )
                        .align(Alignment.TopStart),
                ) {
                    Image(
                        painter = painterResource(R.drawable.big_villager_face),
                        contentDescription = "My Image 2",
                        modifier = Modifier
                            .align(Alignment.TopStart)
                            .width(20.dp)
                    )
                }
                IconButton(
                    onClick = onCreeperClick,
                    modifier = Modifier
                        .padding(top = 130.dp, start = 10.dp)
                        .border(
                            BorderStroke(2.dp, Color(0xFF83816E)),
                            shape = RoundedCornerShape(8.dp)
                        )
                        .align(Alignment.TopStart),
                ) {
                    Image(
                        painter = painterResource(R.drawable.creeper),
                        contentDescription = "My Image 2",
                        modifier = Modifier
                            .align(Alignment.TopStart)
                            .width(20.dp)
                    )
                }

                IconButton(
                    onClick = onFunnyClick,
                    modifier = Modifier
                        .padding(top = 190.dp, start = 10.dp)
                        .border(
                            BorderStroke(2.dp, Color(0xFF83816E)),
                            shape = RoundedCornerShape(8.dp)
                        )
                        .align(Alignment.TopStart),
                ) {
                    Image(
                        painter = painterResource(R.drawable.questionmark),
                        contentDescription = "My Image 2",
                        modifier = Modifier
                            .align(Alignment.TopStart)
                            .width(20.dp)
                    )
                }


                Box(
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .padding(16.dp)
                ) {
                    IconButton(onClick = onStoneClick) {
                        Image(
                            painter = painterResource(id = R.drawable.stoneblock),
                            contentDescription = "My Image",
                            modifier = Modifier
                                .width(100.dp)
                                .height(100.dp)
                        )
                    }
                }

                Box(
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .padding(bottom = (100.dp), start = (60.dp))
                ) {

                    Image(
                        painter = painterResource(id = picareta),
                        contentDescription = "My Image",
                        modifier = Modifier
                            .width(100.dp)
                            .height(100.dp)
                    )
                }
            }


            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
//            Scoreboard(wins = wins, draws = draws, losses = losses)
//            Cards(dealerHand, showAllCards = showAllCards)
//            if(showAllCards)
//                GameResult(
//                    playerPoints = playerPoints,
//                    dealerPoints = dealerPoints,
//                    onPlayAgainButtonClick = onPlayAgainButtonClick,)
//
//            Cards(playerHand, showAllCards = true)
//            PlayerActions(onHitButtonClick, onHoldButtonClick, showAllCards)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    KotlinClickerTheme {
        MineClicker()
    }
}