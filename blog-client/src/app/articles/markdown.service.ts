import { Injectable } from '@angular/core';

const stripRenderer = new marked.Renderer();
const blockStrip = function(input: string) { return input + ' '; };
const inlineStrip = function(input: string) { return input; };
const linkStrip = function(link: string, title: string, text: string) { return text; };
const blankFn = function() { return '' };
stripRenderer.code = blockStrip;
stripRenderer.blockquote = blockStrip;
stripRenderer.html = blockStrip;
stripRenderer.heading = blockStrip;
stripRenderer.hr = blankFn;
stripRenderer.list = blockStrip;
stripRenderer.listitem = blockStrip;
stripRenderer.paragraph = blockStrip;
stripRenderer.table = blockStrip;
stripRenderer.tablerow = blockStrip;
stripRenderer.tablecell = blockStrip;
stripRenderer.strong = inlineStrip;
stripRenderer.em = inlineStrip;
stripRenderer.codespan = inlineStrip;
stripRenderer.br = blankFn;
stripRenderer.del = inlineStrip;
stripRenderer.link = linkStrip;
stripRenderer.image = linkStrip;

@Injectable()
export class MarkdownService {

  constructor() { }

  strip(text: string) {
    return marked(text, { renderer: stripRenderer });
  }
}
