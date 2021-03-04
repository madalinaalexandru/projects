#include <stdio.h>
#include <string.h>
#include <sys/types.h>
#include <dirent.h>
#include <sys/stat.h>
#include <unistd.h>
#include <stdlib.h>
#include <fcntl.h>
#define MAX_PATH_LENGTH 512
#define BUFF_SIZE 7 

typedef struct section{

	char name[7];
	int type;
	int offset;
	int size;
}section;

//pentru listarea fisierelor si directoarelor am folosit si bucati de cod din laboratoare

void nonRecursiveList(char *path){
	
	DIR *dir = NULL;
	struct dirent *entry = NULL;
	char* filePath = malloc(MAX_PATH_LENGTH); 
	dir = opendir(path);
	
	if(dir == NULL){
		
		perror("ERROR\nCould not open directory\n");
	}
	else{
		
		printf("SUCCESS\n");
		
		while((entry = readdir(dir)) != NULL){

			if(strcmp(entry->d_name, ".") != 0 && strcmp(entry->d_name, "..") != 0){
				
				snprintf(filePath, MAX_PATH_LENGTH, "%s/%s", path, entry->d_name);
				printf("%s\n", filePath);
			}
		}	
	}

	free(filePath);
	closedir(dir);
}

void nonRecursiveSzGreater(char *path, int size){

	DIR *dir = NULL;
	struct dirent *entry = NULL;
	char* filePath = malloc(MAX_PATH_LENGTH); 
	dir = opendir(path);
	struct stat statbuf;

	if(dir == NULL){
		
		perror("ERROR\nCould not open directory\n");
	}
	else{
		
		printf("SUCCESS\n");
		
		while((entry = readdir(dir)) != NULL){

			if(strcmp(entry->d_name, ".") != 0 && strcmp(entry->d_name, "..") != 0){
				
				snprintf(filePath, MAX_PATH_LENGTH, "%s/%s", path, entry->d_name);

				if(lstat(filePath, &statbuf) == 0){

					if(S_ISREG(statbuf.st_mode) && (statbuf.st_size > size)){

						printf("%s\n", filePath);
					}
				}
			}
		}	
	}

	free(filePath);
	closedir(dir);
}

void nonRecursiveHasPermWr(char *path){

	DIR *dir = NULL;
	struct dirent *entry = NULL;
	char* filePath = malloc(MAX_PATH_LENGTH); 
	dir = opendir(path);
	struct stat statbuf;

	if(dir == NULL){
		
		perror("ERROR\nCould not open directory\n");
	}
	else{
		
		printf("SUCCESS\n");
		
		while((entry = readdir(dir)) != NULL){

			if(strcmp(entry->d_name, ".") != 0 && strcmp(entry->d_name, "..") != 0){
				
				snprintf(filePath, MAX_PATH_LENGTH, "%s/%s", path, entry->d_name);

				if(lstat(filePath, &statbuf) == 0){

					if(((statbuf.st_mode & 0777) >> 6) == 07 || ((statbuf.st_mode & 0777) >> 6) == 06 || ((statbuf.st_mode & 0777) >> 6) == 02 || ((statbuf.st_mode & 0777) >> 6) == 03){

						printf("%s\n", filePath);
					}
				}
			}
		}	
	}

	free(filePath);
	closedir(dir);
}

void nonRecursiveFilterList(char *path, char *filter){
 
	char* option = malloc(15);
	int size = 0;

	if(strcmp(filter, "has_perm_write") == 0){
		
		strcpy(option, "has_perm_write");
		option[14] = 0;
	}
	else{

		strcpy(option, "size_greater");
		option[12] = 0;

		sscanf(filter+13, "%d", &size);
	}

	if(strcmp(option, "has_perm_write") == 0){
		nonRecursiveHasPermWr(path);
	}
	else{
		nonRecursiveSzGreater(path, size);
	}

	free(option);
}

void recursiveList(char *path){
	
	DIR *dir = NULL;
	struct dirent *entry = NULL;
	char* fullPath = malloc(MAX_PATH_LENGTH);
	struct stat statbuf;

	dir = opendir(path);

	if(dir == NULL){
		
		perror("ERROR\ninvalid directory path\n");
	}
	else{
		
		while((entry = readdir(dir)) != NULL){
			
			if(strcmp(entry->d_name, ".") != 0 && strcmp(entry->d_name, "..") != 0){
				
				snprintf(fullPath, MAX_PATH_LENGTH, "%s/%s", path, entry->d_name);
				
				if(lstat(fullPath, &statbuf) == 0){
					
					printf("%s\n", fullPath);
					
					if(S_ISDIR(statbuf.st_mode))
						
						recursiveList(fullPath);
				}
			}
		}
	}

	free(fullPath);
	closedir(dir);
}

void recursiveHasPermWr(char* path){

	DIR *dir = NULL;
	struct dirent *entry = NULL;
	char* fullPath = malloc(MAX_PATH_LENGTH);
	struct stat statbuf;

	dir = opendir(path);

	if(dir == NULL){
		
		perror("ERROR\ninvalid directory path\n");
	}
	else{
		
		while((entry = readdir(dir)) != NULL){
			
			if(strcmp(entry->d_name, ".") != 0 && strcmp(entry->d_name, "..") != 0){
				
				snprintf(fullPath, MAX_PATH_LENGTH, "%s/%s", path, entry->d_name);
				
				if(lstat(fullPath, &statbuf) == 0){

					if(((statbuf.st_mode & 0777) >> 6) == 07 || ((statbuf.st_mode & 0777) >> 6) == 06 || ((statbuf.st_mode & 0777) >> 6) == 02 || ((statbuf.st_mode & 0777) >> 6) == 03){

						printf("%s\n", fullPath);
					}
					
					if(S_ISDIR(statbuf.st_mode))
						
						recursiveHasPermWr(fullPath);
				}
			}
		}
	}

	free(fullPath);
	closedir(dir);
}

void recursiveSzGreater(char* path, int size){

	DIR *dir = NULL;
	struct dirent *entry = NULL;
	char* fullPath = malloc(MAX_PATH_LENGTH);
	struct stat statbuf;

	dir = opendir(path);

	if(dir == NULL){
		
		perror("ERROR\ninvalid directory path\n");
	}
	else{
		
		while((entry = readdir(dir)) != NULL){
			
			if(strcmp(entry->d_name, ".") != 0 && strcmp(entry->d_name, "..") != 0){
				
				snprintf(fullPath, MAX_PATH_LENGTH, "%s/%s", path, entry->d_name);
				
				if(lstat(fullPath, &statbuf) == 0){

					if(S_ISREG(statbuf.st_mode) && (statbuf.st_size > size)){

						printf("%s\n", fullPath);
					}
					
					if(S_ISDIR(statbuf.st_mode))
						
						recursiveSzGreater(fullPath, size);
				}
			}
		}
	}

	free(fullPath);
	closedir(dir);
}

void recursiveFilterList(char* path, char* filter){

	char* option = malloc(15);
	int size = 0;

	if(strcmp(filter, "has_perm_write") == 0){
		
		strcpy(option, "has_perm_write");
		option[14] = 0;
	}
	else{

		strcpy(option, "size_greater");
		option[12] = 0;

		sscanf(filter+13, "%d", &size);
	}

	if(strcmp(option, "has_perm_write") == 0){
		recursiveHasPermWr(path);
	}
	else{
		recursiveSzGreater(path, size);
	}

	free(option);
}

int testSF(char* file){
	
	int versionNb = 0;
	int nbOfSections = 0;
	int type = 0;
	int fd = -1;
	char* buff = malloc(BUFF_SIZE);

	fd = open(file, O_RDONLY);

	if(fd == -1) {
		perror("ERROR\nwrong input\n");
		return -1;
	}

	read(fd, buff, 1);
	buff[1] = 0;

	if(strcmp(buff, "g") != 0){
		free(buff);
		close(fd);
		return -1;
	}
	
	lseek(fd, 2, SEEK_CUR);

	read(fd, &versionNb, 2);

	if(versionNb < 59 || versionNb > 132){
		free(buff);
		close(fd);
		return -1;
	}

	read(fd, &nbOfSections, 1);

	if(nbOfSections < 8 || nbOfSections > 11){
		free(buff);
		close(fd);
		return -1;
	}

	for(int i = 0; i < nbOfSections; i++){

		lseek(fd, 6, SEEK_CUR);

		read(fd, &type, 4);

		if(type != 13 && type != 90 && type != 49 && type != 45 && type != 48){

			free(buff);
			close(fd);

			return -1;
		}
		
		lseek(fd, 8, SEEK_CUR);
	}

	free(buff);
	close(fd);
	return 0;
}

int parse(char* file){

	int versionNb = 0;
	int nbOfSections = 0;
	int type = 0;
	int fd = -1;
	int size = 0;
	char* buff = malloc(BUFF_SIZE);
	char* name = malloc(7);

	fd = open(file, O_RDONLY);

	if(fd == -1) {
		perror("ERROR\nwrong input\n");
	}

	read(fd, buff, 1);
	buff[1] = 0;
	if(strcmp(buff, "g") != 0){
		printf("ERROR\nwrong magic\n");
		free(name);
		free(buff);
		close(fd);
		return -1;
	}
	
	read(fd, buff, 2);

	read(fd, &versionNb, 2);

	if(versionNb < 59 || versionNb > 132){
		printf("ERROR\nwrong version\n");
		free(name);
		free(buff);
		close(fd);
		return -1;
	}

	read(fd, &nbOfSections, 1);

	if(nbOfSections < 8 || nbOfSections > 11){
		printf("ERROR\nwrong sect_nr\n");
		free(name);
		free(buff);
		close(fd);
		return -1;
	}

	char** sections = malloc(11 * sizeof(char*));

		for(int i = 0; i < 11; i++){
			sections[i] = malloc(27);
		}

	for(int i = 0; i < nbOfSections; i++){

		read(fd, name, 6);
		name[6] = 0;

		read(fd, &type, 4);

		if(type != 13 && type != 90 && type != 49 && type != 45 && type != 48){
			printf("ERROR\nwrong sect_types\n");

			for(int i = 0; i < 11; i++){
				free(sections[i]);
			}	

			free(sections);
			free(name);
			free(buff);
			close(fd);

			return -1;
		}
		
		read(fd, buff, 4);

		read(fd, &size, 4);

		snprintf(sections[i], 27, "section%d: %s %d %d", i+1, name, type, size);
	}

	printf("SUCCESS\nversion=%d\nnr_sections=%d\n", versionNb, nbOfSections);

	for(int i = 0; i < nbOfSections; i++){
		printf("%s\n", sections[i]);
	}

	for(int i = 0; i < 11; i++){
		free(sections[i]);
	}

	free(name);
	free(sections);
	free(buff);
	close(fd);
	return 0;
}

void extract(char* file, char* sect, char* line){

	if(testSF(file) != 0){
		printf("ERROR\ninvalid file\n");
		return;
	}

	int sectionNb = 0;
	int lineNb = 0;
	int fd = -1;
	int nbOfSections = 0;
	char* buff = malloc(BUFF_SIZE);

	fd = open(file, O_RDONLY);

	if(fd == -1) {
		perror("ERROR\ninvalid file");
		free(buff);
		close(fd);
		return;
	}

	sscanf(sect, "%d", &sectionNb);
	sscanf(line, "%d", &lineNb); 

	read(fd, buff, 5);

	read(fd, &nbOfSections, 1);

	if(sectionNb > nbOfSections){
		perror("ERROR\ninvalid section");
		free(buff);
		close(fd);
		return;
	}

	section* sectiuni = malloc(nbOfSections * sizeof(section));

	for(int i = 0; i < nbOfSections; i++){
		read(fd, sectiuni[i].name, 6);
		sectiuni[i].name[6] = 0;
		read(fd, &sectiuni[i].type, 4);
		read(fd, &sectiuni[i].offset, 4);
		read(fd, &sectiuni[i].size, 4);
	}

	lseek(fd, sectiuni[sectionNb-1].offset, SEEK_SET);
	
	int i = 1;
	int size = 0;

	char* sectiune = malloc(sectiuni[sectionNb-1].size);

	read(fd, sectiune, sectiuni[sectionNb-1].size);
	sectiune[sectiuni[sectionNb-1].size] = 0;

	int j = 0;

	while(i < lineNb && size < sectiuni[sectionNb].size){
		size++;

		if(sectiune[j] == '\n'){
			i++;
		}

		j++;
	}

	if(i < lineNb || i > lineNb){

		perror("ERROR\ninvalid line");
		free(buff);
		free(sectiuni);
		free(sectiune);
		close(fd);
		return;
	}
	
	int start = j;	
	printf("SUCCESS\n");
	while(sectiune[j] != '\n'){
		j++;
	}

	j--;

	while(j >= start){
		printf("%c", sectiune[j]);
		j--;
	}

	printf("\n");

	free(sectiune);
	free(sectiuni);
	free(buff);
	close(fd);
}

void findall(char* path){
	
	DIR *dir = NULL;
	struct dirent *entry = NULL;
	char* fullPath = malloc(MAX_PATH_LENGTH);
	struct stat statbuf;

	dir = opendir(path);

	if(dir == NULL){
		
		perror("ERROR\ninvalid directory path\n");
	}
	else{
		
		while((entry = readdir(dir)) != NULL){
			
			if(strcmp(entry->d_name, ".") != 0 && strcmp(entry->d_name, "..") != 0){
				
				snprintf(fullPath, MAX_PATH_LENGTH, "%s/%s", path, entry->d_name);
				
				if(lstat(fullPath, &statbuf) == 0){
					
					if(S_ISREG(statbuf.st_mode)){

						if(testSF(fullPath) == 0){
							
							int nbOfSections = 0;
							char* buff = malloc(BUFF_SIZE);
							int fd = -1;
							int rows = 0;
							int currSize = 0;
							fd = open(fullPath, O_RDONLY);
							int size = 0;

							lseek(fd, 5, SEEK_CUR);

							read(fd, &nbOfSections, 1);

							section* sectiuni = malloc(nbOfSections * sizeof(section));

							for(int i = 0; i < nbOfSections; i++){
								lseek(fd, 10, SEEK_CUR);
								read(fd, &sectiuni[i].offset, 4);
								read(fd, &sectiuni[i].size, 4);
								size += sectiuni[i].size;
							}

							char* currSection = malloc(size);

							for(int i = 0; i < nbOfSections; i++){
								lseek(fd, sectiuni[i].offset, SEEK_SET);
								memset(currSection, 0, size);
								read(fd, currSection, sectiuni[i].size);
								currSection[sectiuni[i].size] = 0;
								int j = 0;

								for(; currSize < sectiuni[i].size; currSize++){

									if(currSection[j] == '\n'){
										rows++;
									}
									j++;

									if(rows == 15){
										rows = 0; 
										currSize = 0;
										printf("%s\n", fullPath);
										break;
									}
								}

								if(currSize == sectiuni[i].size){
									rows = 0; 
									currSize = 0;
								}
								else{
									break;
								}
							}
							
							close(fd);
							free(sectiuni);
							free(buff);
							free(currSection);
						}
					}
					
					if(S_ISDIR(statbuf.st_mode))
						
						findall(fullPath);
				}
			}
		}
	}

	free(fullPath);
	closedir(dir);
}

int main(int argc, char **argv){
    if(argc >= 2){
        if(strcmp(argv[1], "variant") == 0){
            printf("10641\n");
        }

		if(strcmp(argv[1], "list") == 0){
			
			if(argc == 3){

				nonRecursiveList(argv[2]+5);			
			}
			else{

				if(argc == 4 && strcmp(argv[2], "recursive") == 0){
					
					printf("SUCCESS\n");
					recursiveList(argv[3]+5);
				}
				else{

					if(argc == 4 && strcmp(argv[2], "recursive") != 0){

						nonRecursiveFilterList(argv[3]+5, argv[2]);
					}
					else{

						if(argc == 5){

							if(strcmp(argv[2], "recursive") == 0){
								
								printf("SUCCESS\n");
								recursiveFilterList(argv[4]+5, argv[3]);
							}
							else{
								if(strcmp(argv[3], "recursive") == 0){

									printf("SUCCESS\n");	
									recursiveFilterList(argv[4]+5, argv[2]);
								}
							}
						}
					}
				}
			}
		}	

		if(strcmp(argv[1], "parse") == 0){
			
			parse(argv[2]+5);
			}
		else
		{
			if(argc == 3){

				if(strcmp(argv[2], "parse")	== 0){
					parse(argv[1]+5);
				}
			}
		}

		if(strcmp(argv[1], "extract") == 0){
			extract(argv[2]+5, argv[3]+8, argv[4]+5);
		}

		if(strcmp(argv[1], "findall") == 0){
			printf("SUCCESS\n");
			findall(argv[2]+5);
		}
	}

    return 0;
}
