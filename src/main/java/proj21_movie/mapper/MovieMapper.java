package proj21_movie.mapper;

import java.util.List;

import org.springframework.stereotype.Component;

import proj21_movie.dto.Movie;

@Component
public interface MovieMapper {
	List<Movie> selectMovieAll();
	Movie selectMovieByNo(Movie movie);
	// 박스오피스 번호
	Movie selectMovieByBoxofficeNo(Movie movie);
	// 박스 오피스
	List<Movie> selectMovieBoxOfficeAll();
	// 상영예정작
	List<Movie> selectMovieCommingSoonAll();
	
	// 영화 제목으로 검색
	List<Movie> selectMovieByTitle(String movTitle); 
	
	int insertMovie(Movie movie);
	int updateMovie(Movie movie);
	int deleteMovie(Movie movie);
}
