SELECT Score, (SELECT count(DISTINCT score)
               FROM Scores
               WHERE score >= s.score) AS Rank
FROM Scores s
ORDER BY Score DESC ;

select a.Score, sum(case when b.Score>=a.Score then 1 end) as Rank
from Scores a, (select distinct Score from Scores ) b
group by a.id order by a.Score desc;