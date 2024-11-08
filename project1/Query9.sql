SELECT s.name, s.snum, s.ssn
FROM students s
WHERE s.name BETWEEN 'Amy' AND 'Christopher'
ORDER BY s.name;
