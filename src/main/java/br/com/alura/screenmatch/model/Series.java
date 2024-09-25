package br.com.alura.screenmatch.model;

import java.util.OptionalDouble;

public class Series {
    private String titulo;
    private Integer totalTemporadas;
    private Double avaliacao;
    private Category genero;
    private String atores;
    private String poster;
    private String sinopse;

    public Series(DataSeries series) {
        this.titulo = series.titulo();
        this.totalTemporadas = series.totalTemporadas();
        this.avaliacao = OptionalDouble.of(Double.parseDouble(series.avaliacao())).orElse(0);
        this.genero = Category.fromString(series.genero().split(",")[0].trim());
        this.atores = series.atores();
        this.poster = series.poster();
        this.sinopse = series.sinopse();
    }

    @Override
    public String toString() {
        return "Series{" +
                "titulo='" + titulo + '\'' +
                ", totalTemporadas=" + totalTemporadas +
                ", avaliacao=" + avaliacao +
                ", genero=" + genero +
                ", atores='" + atores + '\'' +
                ", poster='" + poster + '\'' +
                ", sinopse='" + sinopse + '\'' +
                '}';
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Integer getTotalTemporadas() {
        return totalTemporadas;
    }

    public void setTotalTemporadas(Integer totalTemporadas) {
        this.totalTemporadas = totalTemporadas;
    }

    public Double getAvaliacao() {
        return avaliacao;
    }

    public void setAvaliacao(Double avaliacao) {
        this.avaliacao = avaliacao;
    }

    public Category getGenero() {
        return genero;
    }

    public void setGenero(Category genero) {
        this.genero = genero;
    }

    public String getAtores() {
        return atores;
    }

    public void setAtores(String atores) {
        this.atores = atores;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getSinopse() {
        return sinopse;
    }

    public void setSinopse(String sinopse) {
        this.sinopse = sinopse;
    }
}
