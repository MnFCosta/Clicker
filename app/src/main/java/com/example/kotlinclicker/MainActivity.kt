package com.example.kotlinclicker
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.kotlinclicker.ui.theme.KotlinClickerTheme

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
                    TelaPrincipal()
                }
            }
        }
    }
}

@Composable
fun TelaPrincipal(){
    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            modifier = Modifier.fillMaxSize(),
            painter = painterResource(id = R.drawable.cavehard),
            contentDescription = "Background",
            contentScale = ContentScale.Crop,
        )
        Box (
            contentAlignment = Alignment.TopCenter
        ){

            Image(
                painter = painterResource(id = R.drawable._1_2_minecraft_emerald),
                contentDescription = "My Image",
                modifier = Modifier
                    .width(30.dp) // Set the width of the image
                    .height(100.dp) // Set the height of the image
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

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    KotlinClickerTheme() {
        TelaPrincipal()
    }
}