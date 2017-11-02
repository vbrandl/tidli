.PHONY: clean
.PHONY: all

all: dot md

dot: $(patsubst %.dot,%.png,$(wildcard *.dot))

md: $(patsubst %.md,%.pdf,$(wildcard *.md))

%.png: %.dot
	dot -Tpng $< -o $@

%.pdf: %.md
	pandoc $< -o $@ --from markdown --template templates/eisvogel.latex --listings --number-sections --toc

clean: clean_md clean_dot

clean_md:
	rm -f *.pdf

clean_dot:
	rm -f *.png
