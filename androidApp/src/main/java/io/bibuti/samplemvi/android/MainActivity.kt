package io.bibuti.samplemvi.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.bibuti.samplemvi.news.NewsState
import io.bibuti.samplemvi.news.NewsViewModel

class MainActivity : ComponentActivity() {

    private val newsViewModel: NewsViewModel by viewModels { ViewModelFactory(ThreadInfoImpl) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            val state = newsViewModel.observeState()
                .collectAsState(initial = NewsState())

            MyApplicationTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    LazyColumn(
                        content = {
                            items(state.value.newsArticleList) { article ->
                                Column(Modifier.padding(4.dp)) {
                                    Text(text = article.title)
                                    Spacer(modifier = Modifier.height(2.dp))
                                    Text(text = article.description, fontStyle = FontStyle.Italic)
                                    Spacer(modifier = Modifier.height(16.dp))
                                }
                            }
                        },
                    )

                }
            }
        }
    }
}

@Composable
fun GreetingView(text: String) {
    Text(text = text)
}

@Preview
@Composable
fun DefaultPreview() {
    MyApplicationTheme {
        GreetingView("Hello, Android!")
    }
}
