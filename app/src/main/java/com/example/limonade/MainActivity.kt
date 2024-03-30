package com.example.limonade

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.limonade.ui.theme.LimonadeTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LimonadeTheme {
                LimonadeApp()
            }
        }
    }
}

@Composable
fun LimonadeApp(
     modifier: Modifier = Modifier
         .fillMaxSize()
         .wrapContentSize(Alignment.Center),// centralização dos componentes
) {
    // A surface container using the 'background' color from the theme
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        // variável que vai ir para os próximos passos com toques
        var proximoPasso by remember { mutableIntStateOf(1) }
        var contagemToques by remember { mutableIntStateOf(0) }

        // condição para mudança de passos
        when (proximoPasso) {

            // CONDIÇÃO 1
            1 -> { // Layouts verticais são criados com a Column()função
                LimonadeTextAndImage(
                    textResource = R.string.tap_the_lemon,
                    resourceImage = R.drawable.lemon_tree ,
                    descriptionResourceImage = R.string.tap_the_lemon,
                    OnImageClick = {
                        proximoPasso = 2
                        contagemToques = (2..4).random() // .randow() método para escoher um valor aleaório dentre os estabelecidos.
                    }
                )
            }
            2 -> { // Layouts verticais são criados com a Column()função
                LimonadeTextAndImage(
                    textResource = R.string.squeeze_lemon,
                    resourceImage = R.drawable.lemon_squeeze ,
                    descriptionResourceImage = R.string.squeeze_lemon,
                    OnImageClick = {
                        contagemToques--
                        if(contagemToques == 0){
                            proximoPasso = 3
                        }

                    }
                )
            }
            3 -> {
                LimonadeTextAndImage(
                    textResource = R.string.drink_lemon,
                    resourceImage = R.drawable.lemon_drink,
                    descriptionResourceImage = R.string.drink_lemon,
                    OnImageClick = {
                        proximoPasso = 4
                    }
                )
            }
            4 -> {
                LimonadeTextAndImage(
                    textResource = R.string.restart_drink,
                    resourceImage = R.drawable.lemon_restart,
                    descriptionResourceImage = R.string.restart_drink,
                    OnImageClick = {
                        proximoPasso = 1
                    }
                )
            }
        }
    }
}

// MÉTODO TEXTO E IMAGEM
//vou colocar em cada condição, mexendo apenas nos parâmetros de cada uma
@Composable
fun LimonadeTextAndImage(
    modifier: Modifier = Modifier,
    textResource: Int,
    resourceImage: Int,
    descriptionResourceImage: Int,
    OnImageClick: () -> Unit,


    ){
    // Layouts verticais são criados com a Column()função
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Text(
            text = stringResource(textResource),
            fontSize = 18.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(16.dp)

        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = OnImageClick,

        ) {
        // para o texto aparecer em cima de uma imagem, usar "Box(modifier"
        Image(
            painter = painterResource(resourceImage), // exibir a imagem da variável
            contentDescription = stringResource(descriptionResourceImage),
            modifier = Modifier
                .wrapContentSize()
        )
            }


    }
}
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    LimonadeTheme {
        LimonadeApp(
        )
    }
}