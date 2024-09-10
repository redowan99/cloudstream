package com.lagradost.cloudstream3.actions.temp

import android.content.Context
import android.content.Intent
import android.net.Uri
import com.lagradost.cloudstream3.actions.VideoClickAction
import com.lagradost.cloudstream3.mvvm.logError
import com.lagradost.cloudstream3.ui.result.LinkLoadingResult
import com.lagradost.cloudstream3.ui.result.ResultEpisode
import com.lagradost.cloudstream3.ui.result.txt
import com.lagradost.cloudstream3.utils.ExtractorLinkType

class PlayInBrowserAction: VideoClickAction() {
    override val name = txt("Play in browser")

    override val oneSource = true

    override val isPlayer = true

    override val sourceTypes: Set<ExtractorLinkType> = setOf(
        ExtractorLinkType.VIDEO,
        ExtractorLinkType.DASH,
        ExtractorLinkType.M3U8
    )

    override fun shouldShow(context: Context?, video: ResultEpisode?) = true

    override fun runAction(
        context: Context?,
        video: ResultEpisode,
        result: LinkLoadingResult,
        index: Int?
    ) {
        val link = result.links.getOrNull(index ?: 0) ?: return
        try {
            val i = Intent(Intent.ACTION_VIEW)
            i.data = Uri.parse(link.url)
            context?.startActivity(i)
        } catch (e: Exception) {
            logError(e)
        }
    }
}