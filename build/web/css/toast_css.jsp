<style>


#toastBox {
    position: absolute;
    bottom: 50px;
    right: 50px;
    display: flex;
    flex-direction: column;
    align-items: flex-end;
    overflow: hidden;
    padding: 20px;
    z-index: 9999;
}

.toastItem {
    width: 400px;
    height: 80px;
    background: #fff;
    box-shadow: 0 0 20px 0 rgba(0,0,0,0.3);
    font-weight: 500;
    margin: 15px 0;
    display: flex;
    align-items: center;
    position: relative;
    transform: translateX(100%);
    animation: moveLeft 0.5s linear forwards;
}

@keyframes moveLeft {
    100% {
        transform: translateX(0);
    }
}

.toastItem i {
    font-size: 30px;
    color: green;
    margin: 0 20px;
}

.error i {
    color: red;
}

.invalid i {
    color: orange;
}

.toastItem::after {
    content: '';
    position: absolute;
    right: 0;
    bottom: 0;
    width: 100%;
    height: 5px;
    background: green;
    z-index: 10;
    animation: decrease 3s linear forwards;
}

.error::after {
    background: red;
}

.invalid::after {
    background: orange;
}

@keyframes decrease {
    100% {
        width: 0;
    }
}

.hidden {
    display: none;
}
</style>
