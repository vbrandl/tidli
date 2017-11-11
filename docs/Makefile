.PHONY: clean all md dot clean_md clean_dot

DOT = $(wildcard *.dot)
PNG = $(DOT:.dot=.png)

MD = $(wildcard *.md)
PDF = $(MD:.md=.pdf)

all: md

dot: $(PNG)

md: dot $(PDF)

%.png: %.dot
	dot -Tpng $< -o $@

%.pdf: %.md dot
	pandoc $< -o $@ --from markdown --template templates/eisvogel.latex --listings --number-sections --toc

clean: clean_md clean_dot

clean_md:
	rm -f *.pdf

clean_dot:
	rm -f *.png

