package me.qingshu.ktorexample.service

import com.vladsch.flexmark.html.HtmlRenderer
import com.vladsch.flexmark.parser.Parser
import java.io.File

class MarkdownService {
    private val parser = Parser.builder().build()
    private val renderer = HtmlRenderer.builder().build()

    fun renderMarkDown(fileName: String): String {
        val normalized = if (fileName.endsWith(".md")) fileName else "$fileName.md"
        val resource = javaClass.getResource("/docs/$normalized") ?: return ""
        val file = File(resource.path)
        require(file.exists()) { "Markdown file not found: $fileName" }
        val document = parser.parse(file.readText(Charsets.UTF_8))
        return renderer.render(document)
    }
}