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
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.kotlinclicker.ui.theme.KotlinClickerTheme



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
fun MineClicker(){
    var esmeraldas by remember {
        mutableStateOf(0)
    }

    TelaPrincipal(esmeraldas = esmeraldas)
    MusicaFundo()
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
    ){
        Image(
            painter = painterResource(R.drawable.__2_minecraft_emerald),
            contentDescription = "Esmeralda",
            modifier = Modifier
                .width(30.dp)
                .height(20.dp)
                .padding(top = 4.dp)
        )
        Text(text = "$qtd", color = Color.Green, modifier = Modifier.padding(start = 5.dp, top=0.dp))
    }
}
/*@Composable
fun Esmeraldas(esmeraldas: String) {
    Text(text = esmeraldas,
        textAlign = TextAlign.Center,
        fontSize = 24.dp,
}*/

@Composable
fun MusicaFundo() {
    val context = LocalContext.current
    val audioPlayer = remember { AudioPlayer(context, R.raw.sweden) }

    DisposableEffect(Unit) {
        audioPlayer.stop()

        onDispose {
            audioPlayer.stop()
        }
    }
}

@Composable
fun TelaPrincipal(esmeraldas: Int){

    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            modifier = Modifier.fillMaxSize(),
            painter = painterResource(id = R.drawable.cavehard),
            contentDescription = "Background",
            contentScale = ContentScale.Crop,
        )
        Box(
            modifier = Modifier
                .fillMaxSize()
                .border(
                    BorderStroke(2.dp, Color(0xBCB6845D)),
                    shape = RoundedCornerShape(8.dp)
                ),
            contentAlignment = Alignment.TopCenter

        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Esmeraldas(qtd = esmeraldas)
            }

            Box(modifier = Modifier
                .width(45.dp)
                .height(220.dp)
                .padding(start = 12.dp, top = 12.dp)
                .border(
                    BorderStroke(2.dp, Color(0xFF83816E)),
                    shape = RoundedCornerShape(8.dp)

                )
                .align(Alignment.TopStart))
            /*IconButton(onClick = { *//*TODO*//* }) {
                Image(
                    painter = painterResource(R.drawable.stonepick),
                    contentDescription = "My Image 2",
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(top = 25.dp, start = 16.dp)
                        .width(40.dp)
                )
            }*/
            Image(
                painter = painterResource(R.drawable.stonepickaxe),
                contentDescription = "My Image 2",
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(top = 11.dp, end = 10.dp)
                    .width(70.dp)
                    .border(
                        BorderStroke(2.dp, Color(0xFF83816E)),
                        shape = RoundedCornerShape(8.dp)

                    ).padding(15.dp)
            )
            Image(
                painter = painterResource(R.drawable.sbeve),
                contentDescription = "My Image 2",
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .padding(top = 20.dp, start = 18.dp)
                    .width(20.dp)
            )
            Image(
                painter = painterResource(R.drawable.big_villager_face),
                contentDescription = "My Image 2",
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .padding(top = 70.dp, start = 18.dp)
                    .width(20.dp)
            )
            Image(
                painter = painterResource(R.drawable.creeper),
                contentDescription = "My Image 2",
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .padding(top = 120.dp, start = 18.dp)
                    .width(20.dp)
            )

            Image(
                painter = painterResource(R.drawable.questionmark),
                contentDescription = "My Image 2",
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .padding(top = 170.dp, start = 18.dp)
                    .width(20.dp)
            )

        }
        Box (
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(16.dp)
        ){

            Image(
                painter = painterResource(id = R.drawable.stoneblock),
                contentDescription = "My Image",
                modifier = Modifier
                    .width(100.dp) // Set the width of the image
                    .height(100.dp) // Set the height of the image
            )
        }

        Box (
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(bottom = 100.dp, start = 70.dp)
        ){

            Image(
                painter = painterResource(id = R.drawable.woodpickaxe),
                contentDescription = "My Image",
                modifier = Modifier
                    .width(100.dp) // Set the width of the image
                    .height(100.dp) // Set the height of the image
            )
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

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    KotlinClickerTheme {
        MineClicker()
    }
}