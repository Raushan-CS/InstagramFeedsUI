package com.example.instagramfeedsui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.instagramfeedsui.ui.theme.InstagramFeedsUITheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            InstagramFeedsUITheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                        FeedsUI()
                }
            }
        }
    }
}


@Composable
fun FeedsUI(modifier: Modifier = Modifier) {
    Scaffold(
        bottomBar = {
            BottomAppBar(
            ) {
                Row(modifier = Modifier.weight(1f)) { // Assign weight to the bottom row
                    // Your bottom composable here
                    InstagramBottomNavigation()
                }
            }
        }
    ) {
        it.calculateBottomPadding()
        Column {
            InstagramLogo()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InstagramLogo(modifier: Modifier = Modifier) {
    TopAppBar(title = {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(end = 5.dp),
            verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Icon(
                painter = painterResource(id = R.drawable.instagram),
                contentDescription = "Instagram Logo",
                modifier = Modifier.size(30.dp)
            )

            Row(
                modifier = modifier.padding(end = 5.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.heart),
                    contentDescription = "Instagram Likes",
                    modifier = Modifier.size(24.dp)
                )
                Spacer(modifier = Modifier.width(15.dp))
                Icon(
                    painter = painterResource(id = R.drawable.chat),
                    contentDescription = "Instagram Likes",
                    modifier = Modifier.size(24.dp)

                )
            }
        }
    })
    InstagramStories(stories = stories)
    InstagramFeed(posts = posts)
}

@Composable
fun StoryItem(
    story: Story,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .size(60.dp)
                .clip(CircleShape)
                .background(Color.Gray)
        ) {
            Image(
                painter = painterResource(id = story.profilePictureResId),
                contentDescription = "Profile Picture",
                modifier = Modifier
                    .size(56.dp)
                    .clip(CircleShape)
                    .border(2.dp, Color.White, CircleShape)
            )
        }
        Text(
            text = story.userName,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.padding(top = 4.dp),
            style = MaterialTheme.typography.bodySmall
        )
    }
}

val stories = listOf(
    Story(
        userName = "Alok",
        profilePictureResId = R.drawable.alok
    ),
    Story(
        userName = "Ashu",
        profilePictureResId = R.drawable.ashu
    ),
    // Add more stories here...
)

@Composable
fun InstagramStories(
    stories: List<Story>,
    modifier: Modifier = Modifier
) {
    LazyRow(
        modifier = modifier,
        contentPadding = PaddingValues(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        item {
            StoryItem(
                story = Story(
                    userName = "Your Story",
                    profilePictureResId = R.drawable.raushan
                ),
                modifier = Modifier.size(60.dp)
            )
        }
        items(stories) { story ->
            StoryItem(
                story = story,
                modifier = Modifier.size(60.dp)
            )
        }
    }
}


@Composable
fun PostItem(
    post: Post,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        // Code for rendering the post item
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = post.profilePictureResId),
                contentDescription = "Profile Picture",
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
            )
            Text(
                text = post.userName,
                modifier = Modifier.padding(start = 8.dp),
                style = MaterialTheme.typography.bodyMedium
            )
        }
        Image(
            painter = painterResource(id = post.postImageResId),
            contentDescription = "Post Image",
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(.74f)
        )
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(top = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                modifier = modifier.padding(start = 8.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.heart),
                    contentDescription = "Like",
                    modifier = Modifier.size(24.dp)
                )
                Text(
                    text = post.likes.toString() + " likes",
                    style = MaterialTheme.typography.bodyMedium
                )
                Icon(
                    painter = painterResource(id = R.drawable.comment),
                    contentDescription = "Comment",
                    modifier = Modifier.size(24.dp)
                )
                Icon(
                    painter = painterResource(id = R.drawable.send),
                    contentDescription = "Send",
                    modifier = Modifier.size(24.dp)
                )
            }
            Icon(
                painter = painterResource(id = R.drawable.save_instagram),
                contentDescription = "Save",
                modifier = Modifier.size(24.dp)
            )
        }
    }
}

@Composable
fun InstagramFeed(
    posts: List<Post>,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier
    ) {
        items(posts) { post ->
            PostItem(post = post)
            Divider(
                modifier = Modifier.padding(horizontal = 16.dp)
            )
        }
    }
}

val posts = listOf(
    Post(
        userName = "Ashu",
        profilePictureResId = R.drawable.ashu,
        postImageResId = R.drawable.ashu,
        likes = 100,
        caption = "This is my first post!"
    ),
    Post(
        userName = "Alok",
        profilePictureResId = R.drawable.alok,
        postImageResId = R.drawable.alok,
        likes = 50,
        caption = "Another cool post!"
    ),
    // Add more posts here...
)

@Composable
fun InstagramBottomNavigation(
    modifier: Modifier = Modifier
) {
    Row(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Icon(
            painter = painterResource(id = R.drawable.home),
            contentDescription = "Like",
            modifier = Modifier.size(24.dp)
        )
        Icon(
            painter = painterResource(id = R.drawable.search),
            contentDescription = "Like",
            modifier = Modifier.size(24.dp)
        )
        Icon(
            painter = painterResource(id = R.drawable.add),
            contentDescription = "Like",
            modifier = Modifier.size(24.dp)
        )
        Icon(
            painter = painterResource(id = R.drawable.reels),
            contentDescription = "Like",
            modifier = Modifier.size(24.dp)
        )
        Icon(
            painter = painterResource(id = R.drawable.profile),
            contentDescription = "Like",
            modifier = Modifier.size(24.dp)
        )

    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    InstagramFeedsUITheme {
        FeedsUI()
    }
}