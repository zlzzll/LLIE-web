import sys

from LibLlie.utils import ConvertFormat
from LibLlie.troditionAlgorithm.utils import script_ta

if __name__ == '__main__': # it can be input local image, url, or bytes stream

    img = script_ta(
        img_path=sys.argv[1],
        algorithm=sys.argv[2],
        color_space=sys.argv[3],

        # following parameters are alternative
        showimg=False,
        saveimg=True,
        name=sys.argv[4],
        # width=800,
        # height=600,
        format=sys.argv[5],
        # directory=results_path,
        # clipLimit=,   # default = 2.0
        # gridSize=,    # default = 8
        # iteration=    # default = 2
    )
    """
        Quickly start:
        
        supported algorithm:
            he: he | clahe | rclahe
        
        supported color_space:
            cs: rgb | hls | hsv | lab | yuv 
            
    """