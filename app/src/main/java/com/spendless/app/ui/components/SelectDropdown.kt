package com.spendless.app.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.spendless.app.R
import com.spendless.app.ui.theme.AppColors

@Composable
fun <T> SelectDropdown(
    items: List<T>,
    selectedItem: T,
    onItemSelected: (T) -> Unit,
    itemContent: @Composable (T) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    Column(modifier = Modifier.fillMaxWidth()) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .shadow(4.dp, shape = RoundedCornerShape(16.dp))
                .background(MaterialTheme.colorScheme.surfaceContainerLowest)
                .clip(RoundedCornerShape(16.dp))
                .clickable { expanded = !expanded }
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.weight(1f)
                ) {
                    itemContent(selectedItem)
                }
                Icon(
                    painter = if (expanded) painterResource(R.drawable.arrow_drop_up) else painterResource(R.drawable.arrow_drop_down),
                    contentDescription = "Expand",
                    tint = MaterialTheme.colorScheme.onSurface,
                    modifier = Modifier.padding(end = 12.dp)
                )
            }
        }

        AnimatedVisibility(
            visible = expanded,
            enter = expandVertically() + fadeIn(),
            exit = shrinkVertically() + fadeOut()
        ) {
            SelectList(
                items = items,
                selectedItem = selectedItem,
                onItemSelected = {
                    onItemSelected(it)
                    expanded = false
                },
                itemContent = itemContent,
                modifier = Modifier.padding(top = 8.dp)
            )
        }
    }
}

@Composable
fun <T> SelectList(
    items: List<T>,
    selectedItem: T,
    onItemSelected: (T) -> Unit,
    itemContent: @Composable (T) -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(240.dp)
            .shadow(8.dp, shape = RoundedCornerShape(16.dp))
            .clip(RoundedCornerShape(16.dp))
            .background(MaterialTheme.colorScheme.surfaceContainerLowest)
    ) {
        LazyColumn {
            items(items) { item ->
                SelectableItem(
                    isSelected = item == selectedItem,
                    onSelect = { onItemSelected(item) }
                ) {
                    itemContent(item)
                }
            }
        }
    }
}

@Composable
fun SelectableItem(
    isSelected: Boolean,
    onSelect: () -> Unit,
    content: @Composable () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onSelect() }
            .padding(end = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(Modifier.weight(1f)) {
            content(
            )
        }
        if (isSelected) {
            Icon(
                imageVector = Icons.Default.Check,
                contentDescription = "Selected",
                tint = MaterialTheme.colorScheme.primary
            )
        }
    }
}